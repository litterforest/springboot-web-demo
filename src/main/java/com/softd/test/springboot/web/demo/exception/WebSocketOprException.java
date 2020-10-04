package com.softd.test.springboot.web.demo.exception;

/**
 * websocket操作异常
 *
 * @author cobee
 * @since 2020-10-04
 */
public class WebSocketOprException extends Exception {
    public WebSocketOprException() {
        super();
    }

    public WebSocketOprException(String message) {
        super(message);
    }

    public WebSocketOprException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebSocketOprException(Throwable cause) {
        super(cause);
    }

    protected WebSocketOprException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
