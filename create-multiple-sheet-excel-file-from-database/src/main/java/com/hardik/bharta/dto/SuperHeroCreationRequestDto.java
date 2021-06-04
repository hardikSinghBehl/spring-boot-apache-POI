package com.hardik.bharta.dto;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JacksonStdImpl
public class SuperHeroCreationRequestDto {

	private final String name;
	private final String alterEgoName;
	private final Integer comicId;

}
