package com.huk.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huk.exception.AuthException;
import com.huk.services.User;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    private final String applicationSecretKey;

    public AuthorizationFilter(AuthenticationManager authenticationManager,
                               String applicationSecretKey) {
        super(authenticationManager);
        this.applicationSecretKey = applicationSecretKey;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String headerValue = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (headerValue != null) {
            Authentication authentication = getAuthentication(headerValue);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(@NonNull String token) {
        String subject = JWT.require(Algorithm.HMAC512(applicationSecretKey))
                            .build()
                            .verify(token)
                            .getSubject();
        try {
            User user = new ObjectMapper().readValue(subject, User.class);
            if (user == null) {
                return null;
            }

            final List<SimpleGrantedAuthority> authorities =
                    user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                        .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(user, null, authorities);
        } catch (JsonProcessingException e) {
            throw new AuthException(e.getMessage(), e);
        }
    }
}
