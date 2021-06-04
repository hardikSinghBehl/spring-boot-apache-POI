package com.hardik.bharta.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JacksonStdImpl
public class SuperHeroCreationRequestDto {

	@Schema(description = "Superhero name", required = true, example = "SomethingMan")
	@NotBlank(message = "superhero name must not be empty")
	private final String name;

	@Schema(description = "Superhero alter-ego name", required = true, example = "Ordinary Anything Name")
	@NotBlank(message = "superhero alter-ego name must not be empty")
	private final String alterEgoName;

	@Schema(description = "1 for Detective Comics, 2 for Marvel Comics", required = true, example = "2")
	@NotNull(message = "comic-id must not be null")
	@DecimalMin(value = "1", message = "comic-id value must be either 1 ,2 or 3")
	@DecimalMax(value = "3", message = "comic-id value must be either 1 ,2 or 3")
	private final Integer comicId;

}
