package com.bocft.bocpet.webapi.common.exception;

public class BocpetBizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BocpetBizException() {
        super();
    }

    public BocpetBizException(String message) {
        super(message);
    }

    public BocpetBizException(Throwable cause) {
        super(cause);
    }

    public BocpetBizException(String message, Throwable cause) {
        super(message, cause);
    }
}
