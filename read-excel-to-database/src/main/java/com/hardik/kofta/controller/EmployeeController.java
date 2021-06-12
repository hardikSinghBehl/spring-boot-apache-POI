package com.hardik.kofta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hardik.kofta.annotation.IsXlsx;
import com.hardik.kofta.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/employee")
@AllArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@IsXlsx
	@PostMapping(value = "/bulk-upload", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "bulk upload employee data in database")
	public ResponseEntity<?> bulkUploadEmployeeDataHandler(
			@Parameter @RequestParam(name = "code", required = true) final String code,
			@Parameter @RequestParam(name = "file", required = true) final MultipartFile file) {
		return employeeService.bulkUpload(code, file);
	}

}
