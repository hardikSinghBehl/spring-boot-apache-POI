package com.hardik.bharta.exception.handler;

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

import com.hardik.bharta.exception.DuplicateSuperHeroNameException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DuplicateSuperHeroNameExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(DuplicateSuperHeroNameException.class)
	public ResponseEntity<?> duplicateSuperHeroNameViolation(DuplicateSuperHeroNameException ex) {

		final var response = new JSONObject();
		response.put("status", "Failure");
		response.put("message", "Super Hero with specified name already exists in the system");
		response.put("timestamp", LocalDateTime.now().toString());
		return ResponseEntity.badRequest().body(response.toString());
	}

}
