package com.exchange.traderapi.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleGeneral(HttpServletRequest req, Exception ex) {
		ex.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(TraderException.class)
    @ResponseBody
    public ResponseEntity<?> handleTrader(HttpServletRequest req, TraderException exception) {
		exception.printStackTrace();
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
}
