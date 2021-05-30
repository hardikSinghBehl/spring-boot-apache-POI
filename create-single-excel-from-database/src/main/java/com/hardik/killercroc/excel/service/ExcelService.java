package com.hardik.killercroc.excel.service;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.hardik.killercroc.service.EmployeeService;
import com.hardik.killercroc.utility.ExcelCellUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExcelService {

	private final EmployeeService employeeService;
	private final List<String> columnNameList = List.of("id", "email-id", "date-of-birth", "first-name", "middle-name",
			"last-name", "job-title", "joining-date", "is-active");

	public void generate(final HttpServletResponse response) throws IOException {
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=employee-record.xlsx");
		final var outputStream = new BufferedOutputStream(response.getOutputStream());
		final var employees = employeeService.getAllEmployees();
		final var workBook = new XSSFWorkbook();
		final var workSheet = workBook.createSheet("Employee Records");
		final var initialRow = workSheet.createRow(0);

		// To make heading row's font bold
		final var style = workBook.createCellStyle();
		final var font = workBook.createFont();
		font.setBold(true);
		style.setFont(font);

		// Writing heading cell names in inital-row
		for (int column = 0; column < columnNameList.size(); column++) {
			final var cell = initialRow.createCell(column);
			cell.setCellStyle(style);
			cell.setCellValue(columnNameList.get(column));
		}

		// Iterating over each employee
		for (int row = 0; row < employees.size(); row++) {

			// making a new row representing the current employee
			final var currentRow = workSheet.createRow(row + 1);

			// populating current employees data in columns
			for (int column = 0; column < columnNameList.size(); column++) {
				final var currentCell = currentRow.createCell(column);
				final var currentEmployee = employees.get(row);
				currentCell.setCellValue(ExcelCellUtils.getValue(column, currentEmployee));
			}

		}

		// auto sizing each column in worksheet
		for (int column = 0; column < columnNameList.size(); column++) {
			workSheet.autoSizeColumn(column);
		}

		workBook.write(outputStream);
		workBook.close();
		outputStream.close();
	}

}
