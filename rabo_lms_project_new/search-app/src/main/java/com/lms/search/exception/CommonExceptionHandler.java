package com.lms.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lms.search.model.ErrorResponse;

import lombok.extern.slf4j.Slf4j;



@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

	
	
	  @ExceptionHandler(BusinessException.class)
	    public ResponseEntity<ErrorResponse> processBusinessException(BusinessException ex) {
	        Exception exception = (ex.originalException == null)?null: ex;
	        return generateErrorResponse(ex.errorCode, ex.message, exception);
	    }

	    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	    public ResponseEntity<HttpStatus> processMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
	        return new ResponseEntity<HttpStatus>(HttpStatus.METHOD_NOT_ALLOWED);
	    }


	    @ExceptionHandler(Exception.class)
	    public  ResponseEntity<ErrorResponse> processBusinessException(Exception ex) {
	    	return generateErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, "Internal Server error", ex);
	    }

	    private ResponseEntity<ErrorResponse> generateErrorResponse(ErrorCode errorCode, String message, Exception ex) {
	    	
	        log.error("Exception Stack", ex);
	    	
	        ErrorResponse errorResponse = new ErrorResponse(errorCode.code, message);
	        return new ResponseEntity<ErrorResponse>(errorResponse, errorCode.httpStatus);
	    }
	

}
