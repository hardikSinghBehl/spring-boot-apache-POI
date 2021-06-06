package com.hardik.pyaaru.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {

	private final List<String> columnNames = List.of("Email-id", "Full-Name", "Salary");

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

}
