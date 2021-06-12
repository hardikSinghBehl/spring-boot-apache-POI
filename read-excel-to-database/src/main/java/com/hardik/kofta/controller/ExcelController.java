package com.hardik.kofta.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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
import com.hardik.kofta.excel.service.ExcelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/excel")
public class ExcelController {

	private final ExcelService excelService;

	@GetMapping(value = "/template")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Download excel file template")
	public ResponseEntity<InputStreamResource> excelTemplateFileDownloadHandler() throws IOException {
		final var template = Thread.currentThread().getContextClassLoader().getResourceAsStream("template.xlsx");
		return ResponseEntity.status(HttpStatus.OK)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=template.xlsx")
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.body(new InputStreamResource(template));
	}

	@IsXlsx
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Validates Data in Excel File")
	public ResponseEntity<?> validateExcelFileHandler(
			@Parameter @RequestParam(name = "file", required = true) final MultipartFile file)
			throws IOException, NoSuchAlgorithmException {
		return excelService.validateData(file);
	}

}
