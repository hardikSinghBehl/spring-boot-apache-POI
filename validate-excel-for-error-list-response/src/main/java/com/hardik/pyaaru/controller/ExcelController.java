package com.hardik.pyaaru.controller;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.pyaaru.service.ExcelService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/excel")
public class ExcelController {

	private final ExcelService excelService;

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Download excel file template")
	public ResponseEntity<InputStreamResource> excelTemplateFileDownloadHandler() throws IOException {
		return ResponseEntity.status(HttpStatus.OK)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=template.xlsx")
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.body(new InputStreamResource(excelService.getTemplate()));
	}

}
