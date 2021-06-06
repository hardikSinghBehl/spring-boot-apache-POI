package com.hardik.durian.dto;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JacksonStdImpl
public class ErrorMessageDto {

	private final Integer row;
	private final Integer cell;
	private final String message;

}
