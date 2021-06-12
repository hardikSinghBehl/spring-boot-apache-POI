package com.hardik.kofta.exception.handler;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hardik.kofta.exception.InvalidCodeException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class InvalidCodeExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(InvalidCodeException.class)
	public ResponseEntity<?> invalidCodeExceptionHandler(InvalidCodeException ex) {

		final var response = new JSONObject();
		response.put("status", "Failure");
		response.put("message", "Invalid code");
		response.put("timestamp", LocalDateTime.now().toString());
		return ResponseEntity.badRequest().body(response.toString());
	}

}
