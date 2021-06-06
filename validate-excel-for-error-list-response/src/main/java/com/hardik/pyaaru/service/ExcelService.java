package com.hardik.pyaaru.service;

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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.hardik.pyaaru.exception.InvalidTemplateFormatException;
import com.hardik.pyaaru.repository.EmployeeRepository;

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
		final var errorMessages = new ArrayList<String>();

		for (int row = 0; row < numberOfRowsInWorkSheet; row++) {
			final var currentRow = workSheet.getRow(row + 1);

			for (int cell = 0; cell < currentRow.getLastCellNum(); cell++) {
				var currentCell = currentRow.getCell(cell);
				if (currentCell != null) {
					if (cell == 0)
						validateCellForEmail(dataFormatter.formatCellValue(currentCell), errorMessages, row + 1,
								cell + 1);
					if (cell == 1)
						validateCellForName(dataFormatter.formatCellValue(currentCell), errorMessages, row + 1,
								cell + 1);
					if (cell == 2)
						validateCellForSalary(dataFormatter.formatCellValue(currentCell), errorMessages, row + 1,
								cell + 1);
				} else
					errorMessages.add("No Value Present At Row " + row + 1 + " Cell " + cell + 1);
			}

		}

		workBook.close();
		if (errorMessages.size() == 0) {
			final var response = new JSONObject();
			response.put("message", "No Errors Found");
			response.put("timestamp", LocalDateTime.now().toString());
			return ResponseEntity.ok(response.toString());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Gson().toJson(errorMessages));
	}

	private void validateCellForEmail(final String cellValue, final ArrayList<String> errorMessages, Integer rowIndex,
			Integer cellIndex) {

		if (!EmailValidator.getInstance().isValid(cellValue))
			errorMessages.add("Email Id Not Valid At Row " + rowIndex + " Cell " + cellIndex);

		if (employeeRepository.existsByEmailId(cellValue))
			errorMessages.add("Email Id " + cellValue + " Already Exists In The System At Row " + rowIndex + " Cell "
					+ cellIndex);

	}

	private void validateCellForName(final String cellValue, final ArrayList<String> errorMessages, Integer rowIndex,
			Integer cellIndex) {

		if (cellValue.matches(".*\\d.*"))
			errorMessages.add("Invalid Name Entered At Row " + rowIndex + " Cell " + cellIndex);
	}

	private void validateCellForSalary(final String cellValue, final ArrayList<String> errorMessages, Integer rowIndex,
			Integer cellIndex) {

		if (!NumberUtils.isParsable(cellValue))
			errorMessages.add("Invalid Salary Amount Entered At Row " + rowIndex + " Cell " + cellIndex);

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

}
