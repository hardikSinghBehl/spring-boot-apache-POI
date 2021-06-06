package com.hardik.durian.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.durian.entity.Employee;
import com.hardik.durian.repository.EmployeeRepository;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/employees")
public class EmployeeController {

	private final EmployeeRepository employeeRepository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Returns List of Employees in system")
	public ResponseEntity<List<Employee>> retreiveEmployees() {
		return ResponseEntity.ok(employeeRepository.findAll());
	}
}