package com.nongfu.kakax.basemain.advice;

import javax.servlet.ServletException;

public class JwtAuthException extends ServletException {
    public JwtAuthException() {
    }

    public JwtAuthException(String message) {
        super(message);
    }

    public JwtAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtAuthException(Throwable cause) {
        super(cause);
    }
}
