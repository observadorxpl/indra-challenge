package com.indra.indrachallenge.infraestructure.output.config.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {

    public CustomAuthenticationException(@Nullable String msg, Throwable cause) {
        super(msg, cause);
    }

    public CustomAuthenticationException(@Nullable String msg) {
        super(msg);
    }
}
