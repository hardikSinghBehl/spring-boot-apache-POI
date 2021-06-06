package com.hardik.durian.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.EmailValidator;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hardik.durian.dto.ErrorMessageDto;
import com.hardik.durian.exception.InvalidTemplateFormatException;
import com.hardik.durian.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcelService {

	private final List<String> columnNames = List.of("Email-id", "Full-Name", "Salary");
	private final DataFormatter dataFormatter = new DataFormatter();
	private final EmployeeRepository employeeRepository;

	public ByteArrayInputStream getTemplate() throws IOException {
		final var workBook = new XSSFWorkbook();
		final var workSheet = workBook.createSheet("Employee Data Registery Template");
		final var initialRow = workSheet.createRow(0);
		final var count = new AtomicInteger(0);

		columnNames.forEach(columnName -> {
			final var cell = initialRow.createCell(count.getAndIncrement());
			styleBold(workBook, cell);
			cell.setCellValue(columnName);
		});

		autoSizeColumns(workSheet);

		final var outputStream = new ByteArrayOutputStream();
		workBook.write(outputStream);

		workBook.close();
		return new ByteArrayInputStream(outputStream.toByteArray());
	}

	private void styleBold(final XSSFWorkbook workBook, final XSSFCell cell) {
		final var style = workBook.createCellStyle();
		final var font = workBook.createFont();
		font.setBold(true);
		style.setFont(font);

		for (int column = 0; column < columnNames.size(); column++) {
			cell.setCellStyle(style);
		}
	}

	private void autoSizeColumns(final XSSFSheet workSheet) {
		for (int column = 0; column < columnNames.size(); column++) {
			workSheet.autoSizeColumn(column);
		}
	}

	public ResponseEntity<?> validateData(final MultipartFile file) throws IOException {
		final var workBook = new XSSFWorkbook(file.getInputStream());
		final var workSheet = workBook.getSheetAt(0);
		final var initialRow = workSheet.getRow(0);
		final var numberOfRowsInWorkSheet = workSheet.getLastRowNum();
		checkForValidFormat(workBook, initialRow);
		final var errorMessages = new ArrayList<ErrorMessageDto>();

		for (int row = 0; row < numberOfRowsInWorkSheet; row++) {
			final var currentRow = workSheet.getRow(row + 1);

			for (int cell = 0; cell < currentRow.getLastCellNum(); cell++) {
				var currentCell = currentRow.getCell(cell);
				if (currentCell != null) {
					if (cell == 0)
						validateCellForEmail(currentCell, errorMessages, row, cell);
					if (cell == 1)
						validateCellForName(currentCell, errorMessages, row, cell);
					if (cell == 2)
						validateCellForSalary(currentCell, errorMessages, row, cell);
				} else
					errorMessages
							.add(ErrorMessageDto.builder().message("No Value Present").row(row).cell(cell).build());
			}

		}

		if (errorMessages.size() == 0) {
			final var response = new JSONObject();
			response.put("message", "No Errors Found");
			response.put("timestamp", LocalDateTime.now().toString());
			return ResponseEntity.ok(response.toString());
		}

		colorCellsWithError(errorMessages, workBook, workSheet);
		createSheetDetailingErrors(errorMessages, workBook);

		final var outputStream = new ByteArrayOutputStream();
		workBook.write(outputStream);

		workBook.close();
		return ResponseEntity.status(HttpStatus.OK)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=employee-registery.xlsx")
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.body(new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray())));
	}

	private void validateCellForEmail(final XSSFCell cell, final ArrayList<ErrorMessageDto> errorMessages,
			Integer rowIndex, Integer cellIndex) {

		final var cellValue = dataFormatter.formatCellValue(cell);

		if (!EmailValidator.getInstance().isValid(cellValue))
			errorMessages
					.add(ErrorMessageDto.builder().message("Email Id Not Valid").row(rowIndex).cell(cellIndex).build());

		if (employeeRepository.existsByEmailId(cellValue))
			errorMessages.add(ErrorMessageDto.builder().message("Email Id Already Exists In The System").row(rowIndex)
					.cell(cellIndex).build());

	}

	private void validateCellForName(final XSSFCell cell, final ArrayList<ErrorMessageDto> errorMessages,
			Integer rowIndex, Integer cellIndex) {
		final var cellValue = dataFormatter.formatCellValue(cell);

		if (cellValue.matches(".*\\d.*"))
			errorMessages.add(ErrorMessageDto.builder().message("Name Entered Is Not Valid").row(rowIndex)
					.cell(cellIndex).build());
	}

	private void validateCellForSalary(final XSSFCell cell, final ArrayList<ErrorMessageDto> errorMessages,
			Integer rowIndex, Integer cellIndex) {
		final var cellValue = dataFormatter.formatCellValue(cell);

		if (!NumberUtils.isParsable(cellValue))
			errorMessages.add(ErrorMessageDto.builder().message("Salary Value Is Not A Valid Numeric Value")
					.row(rowIndex).cell(cellIndex).build());
	}

	private void checkForValidFormat(final XSSFWorkbook workBook, final XSSFRow initialRow) throws IOException {
		if (initialRow.getLastCellNum() != columnNames.size())
			throw new InvalidTemplateFormatException();

		for (int cell = 0; cell < initialRow.getLastCellNum(); cell++) {
			final var currentCell = initialRow.getCell(cell);
			if (!dataFormatter.formatCellValue(currentCell).equals(columnNames.get(cell))) {
				workBook.close();
				throw new InvalidTemplateFormatException();
			}
		}
	}

	private void createSheetDetailingErrors(ArrayList<ErrorMessageDto> errorMessages, XSSFWorkbook workBook) {
		final var workSheet = workBook.createSheet("Error Details");
		final var columnNames = List.of("Row Number", "Cell Number", "Error Details");
		final var initialRow = workSheet.createRow(0);
		final var initialRowCellCount = new AtomicInteger(0);
		final var dataRowCount = new AtomicInteger(1);

		columnNames.forEach(columnName -> {
			final var cell = initialRow.createCell(initialRowCellCount.getAndIncrement());
			styleBold(workBook, cell);
			cell.setCellValue(columnName);
		});

		for (int row = 0; row < errorMessages.size(); row++) {
			final var currentRow = workSheet.createRow(row + 1);

			for (int cell = 0; cell < columnNames.size(); cell++) {
				final var currentCell = currentRow.createCell(cell);
				final var errorMessage = errorMessages.get(row);
				if (cell == 0)
					currentCell.setCellValue(errorMessage.getRow() + 2);
				if (cell == 1)
					currentCell.setCellValue(errorMessage.getCell() + 1);
				if (cell == 2)
					currentCell.setCellValue(errorMessage.getMessage());
			}
		}

		autoSizeColumns(workSheet, columnNames);
	}

	private void autoSizeColumns(final XSSFSheet workSheet, final List<String> columnNames) {
		for (int column = 0; column < columnNames.size(); column++) {
			workSheet.autoSizeColumn(column);
		}
	}

	private void colorCellsWithError(final ArrayList<ErrorMessageDto> errorMessages, final XSSFWorkbook workBook,
			final XSSFSheet workSheet) {
		errorMessages.forEach(errorMessage -> {
			final var style = workBook.createCellStyle();
			final var font = workBook.createFont();
			font.setColor(IndexedColors.WHITE.getIndex());
			style.setFont(font);
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			final var row = workSheet.getRow(errorMessage.getRow() + 1);
			final var cell = row.getCell(errorMessage.getCell());
			cell.setCellStyle(style);
		});
	}

}
