package com.huk.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException {
    public AuthException(String msg) {
        super(msg);
    }

    public AuthException(String msg, Throwable t) {
        super(msg, t);
    }
}