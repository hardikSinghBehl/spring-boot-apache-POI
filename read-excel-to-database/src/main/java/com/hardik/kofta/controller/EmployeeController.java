package com.hardik.kofta.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hardik.kofta.annotation.IsXlsx;
import com.hardik.kofta.constant.ApiPath;
import com.hardik.kofta.constant.ApiSummary;
import com.hardik.kofta.entity.Employee;
import com.hardik.kofta.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = ApiPath.EMPLOYEE_BASE_PATH)
@AllArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping(value = ApiPath.ALL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = ApiSummary.EMPLOYEE_LIST)
	public ResponseEntity<List<Employee>> getEmployees() {
		return employeeService.retreiveEmployees();
	}

	@IsXlsx
	@PostMapping(value = ApiPath.BULK_UPLOAD, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = ApiSummary.BULK_UPLOAD)
	public ResponseEntity<?> bulkUploadEmployeeDataHandler(
			@Parameter @RequestParam(name = "code", required = true) final Integer code,
			@Parameter @RequestParam(name = "file", required = true) final MultipartFile file)
			throws NumberFormatException, ExecutionException, NoSuchAlgorithmException, IOException {
		return employeeService.bulkUpload(code, file);
	}

}
