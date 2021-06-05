package com.hardik.bharta.controller;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.bharta.constant.ApiConstant;
import com.hardik.bharta.excel.service.ExcelService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = ApiConstant.EXCEL_CONTROLLER_BASE_PATH)
@AllArgsConstructor
public class ExcelController {

	private final ExcelService excelService;

	@GetMapping(value = ApiConstant.GENERATE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = ApiConstant.GENERATE_EXCEL_SUMMARY)
	public ResponseEntity<InputStreamResource> generateAndDownloadExcelFile() throws IOException {
		return excelService.generate();
	}

}
