package com.hardik.killercroc.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JacksonStdImpl
public class EmployeeCreationRequestDto {

	@Schema(description = "email-id of employee", required = true, example = "hardik.behl7444@gmail.com")
	@NotBlank(message = "Email-id must not be empty")
	@Email(message = "Email-id must be of a valid format")
	@Size(max = 50, message = "email-id must not exceed 50 characters")
	private final String emailId;

	@Schema(description = "first-name of employee", required = true, example = "Hardik")
	@NotBlank(message = "First-name must not be empty")
	@Size(max = 50, message = "first-name must not exceed 50 characters")
	private final String firstName;

	@Schema(description = "middle-name of employee", required = false, example = "Singh")
	@Size(max = 50, message = "middle-name must not exceed 50 characters")
	private final String middleName;

	@Schema(description = "last-name of employee", required = true, example = "Behl")
	@NotBlank(message = "Last-name must not be empty")
	@Size(max = 50, message = "last-name must not exceed 50 characters")
	private final String lastName;

	@Schema(description = "date-of-birth of employee", required = true, example = "1990-12-25")
	@NotBlank(message = "Date-of-birth must not be empty")
	@Past(message = "date-of-birth must be in the past")
	private final LocalDate dateOfBirth;

	@Schema(description = "joining-date of employee", required = true, example = "2021-05-14")
	@NotBlank(message = "joining-date must not be empty")
	private final LocalDate joiningDate;

	@Schema(description = "job-title of employee", required = true, example = "Backend Developer")
	@NotBlank(message = "Job-title must not be empty")
	private final String jobTitle;

	@Schema(description = "boolean flag to represent if employee is still working", required = true, example = "true")
	@NotBlank(message = "Active field must not be empty")
	private final boolean active;

}
