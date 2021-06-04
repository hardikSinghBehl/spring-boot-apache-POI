package com.hardik.bharta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.bharta.dto.SuperHeroCreationRequestDto;
import com.hardik.bharta.entity.SuperHero;
import com.hardik.bharta.service.SuperHeroService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/super-hero")
@AllArgsConstructor
public class SuperHeroController {

	private final SuperHeroService superHeroService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Returns list of superheroes stored in system")
	public ResponseEntity<List<SuperHero>> superHeroRetreivalHandler() {
		return superHeroService.retreiveAll();
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Creates super-hero record in system")
	public ResponseEntity<?> superHeroCreationHandler(
			@Valid @RequestBody(required = true) final SuperHeroCreationRequestDto superHeroCreationRequest) {
		return superHeroService.create(superHeroCreationRequest);
	}

}
