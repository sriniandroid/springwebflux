package com.wf.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wf.demo.rest.ServiceResponse;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
 
	@ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceResponse serverExceptionHandler(Exception ex) { 
        return buildServiceResponse(ex, HttpStatus.BAD_REQUEST.value());
    }
	
	public ServiceResponse buildServiceResponse(Exception ex, int errorCode) {
		return ServiceResponse.builder()
				.statusCode(errorCode + "")
				.errorMessage(ex.getMessage())
				.build();
	}
	 
}
