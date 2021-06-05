package com.hardik.bharta.controller;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.bharta.excel.service.ExcelService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/excel")
@AllArgsConstructor
public class ExcelController {

	private final ExcelService excelService;

	@GetMapping("/generate")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<InputStreamResource> generateAndDownloadExcelFile() throws IOException {
		return excelService.generate();
	}

}
