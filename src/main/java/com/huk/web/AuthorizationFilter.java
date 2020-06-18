package com.huk.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huk.exception.AuthException;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

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

            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        } catch (JsonProcessingException e) {
            throw new AuthException(e.getMessage(), e);
        }
    }
}
