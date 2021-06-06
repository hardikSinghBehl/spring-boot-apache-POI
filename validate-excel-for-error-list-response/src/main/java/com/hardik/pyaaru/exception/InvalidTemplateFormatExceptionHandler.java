package com.hardik.pyaaru.exception;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidTemplateFormatExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(InvalidTemplateFormatException.class)
	public ResponseEntity<?> duplicateSuperHeroNameViolation(InvalidTemplateFormatException ex) {

		final var response = new JSONObject();
		response.put("status", "Failure");
		response.put("message", "Excel File Does Not Conform To Given Template");
		response.put("timestamp", LocalDateTime.now().toString());
		return ResponseEntity.badRequest().body(response.toString());
	}

}
