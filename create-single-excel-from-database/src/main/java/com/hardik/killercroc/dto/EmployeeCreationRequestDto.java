package com.hardik.killercroc.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JacksonStdImpl
public class EmployeeCreationRequestDto {

	private final String emailId;
	private final String firstName;
	private final String middleName;
	private final String lastName;
	private final LocalDate dateOfBirth;
	private final LocalDate joiningDate;
	private final String jobTitle;
	private final boolean active;

}
