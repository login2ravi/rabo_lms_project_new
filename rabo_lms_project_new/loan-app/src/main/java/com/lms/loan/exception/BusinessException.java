package com.lms.loan.exception;

public class BusinessException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final ErrorCode errorCode;
    final String message;
    Exception originalException;

    public BusinessException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public BusinessException(ErrorCode errorCode, String message, Exception exception) {
        this.errorCode = errorCode;
        this.message = message;
        this.originalException = exception;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public Exception getOriginalException() {
		return originalException;
	}
    
    
}