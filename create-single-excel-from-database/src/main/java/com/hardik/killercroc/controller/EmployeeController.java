package com.hardik.killercroc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.killercroc.dto.EmployeeCreationRequestDto;
import com.hardik.killercroc.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Creates a new employee record in the system")
	public ResponseEntity<?> employeeCreationhandler(
			@RequestBody(required = true) final EmployeeCreationRequestDto employeeCreationRequestDto) {
		return employeeService.createEmployee(employeeCreationRequestDto);
	}
}
