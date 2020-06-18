package com.huk.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huk.enums.UserRole;
import com.huk.exception.AuthException;
import com.huk.services.Role;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final String applicationSecretKey;

    public AuthenticationFilter(AuthenticationManager authenticationManager, String applicationSecretKey) {
        this.authenticationManager = authenticationManager;
        this.applicationSecretKey = applicationSecretKey;
    }

    //метод пробует залогинить, идентификацировать нашего пользователя
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try (ServletInputStream is = request.getInputStream()) {
            final ObjectMapper objectMapper = new ObjectMapper();
            final LoginUserDto loginUserDto = objectMapper.readValue(is, LoginUserDto.class);

            final String email = loginUserDto.getEmail();
            final String password = loginUserDto.getPassword();

            Authentication authentication = new UsernamePasswordAuthenticationToken(email,
                                                                                    password,
                                                                                    Collections.emptyList());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new AuthException(e.getMessage(), e);
        }
    }

    //удачная идентификация, юзер наш
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        Object principal = authResult.getPrincipal();
        if (!(principal instanceof User)) {
            throw new AuthException("User not authenticated...");
        }

        User user = (User) principal;

        try {
            String subject = new ObjectMapper().writeValueAsString(toUserModel(user));
            Date expiresAt = new Date(System.currentTimeMillis() + 300_000);
            Algorithm algorithm = Algorithm.HMAC512(applicationSecretKey);
            String token = JWT.create().withSubject(subject).withExpiresAt(expiresAt).sign(algorithm);
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
            response.addHeader(HttpHeaders.AUTHORIZATION, token);
        } catch (JsonProcessingException e) {
            throw new AuthException(e.getMessage(), e);
        }
    }

    private com.huk.services.User toUserModel(User user) {
        com.huk.services.User result = new com.huk.services.User();
        result.setEmail(user.getUsername());

        final List<Role> roles = user.getAuthorities()
                                     .stream()
                                     .map(authority -> {
                                         final Role role = new Role();
                                         role.setRole(UserRole.defineRole(authority.getAuthority()));
                                         return role;
                                     })
                                     .collect(Collectors.toList());
        result.setRoles(roles);
        return result;
    }
}
