package com.hardik.bharta.excel.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hardik.bharta.entity.SuperHero;
import com.hardik.bharta.excel.utility.ExcelCellUtils;
import com.hardik.bharta.repository.MasterComicRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExcelService {

	private final MasterComicRepository masterComicRepository;
	private final List<String> workSheetNames = List.of("Detective Comics", "Marvel Comics");
	private final List<String> columnNames = List.of("ID", "Name", "Alter Ego", "Comic", "Created-At");
	private final XSSFWorkbook workBook = new XSSFWorkbook();

	public ResponseEntity<InputStreamResource> generate() throws IOException {

		workSheetNames.forEach(workSheetName -> {
			final var workSheet = workBook.createSheet(workSheetName);
			final var initialRow = workSheet.createRow(0);
			writeInitialRow(initialRow);
			writeSuperHeroDataInSheet(workSheet, masterComicRepository.findByName(workSheetName).get().getSuperHeros());
			autoSizeColumns(workSheet);
		});

		final var outputStream = new ByteArrayOutputStream();
		workBook.write(outputStream);

		workBook.close();
		return ResponseEntity.status(HttpStatus.OK)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=Superheroes.xlsx")
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.body(new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray())));
	}

	private void writeInitialRow(final XSSFRow initialRow) {
		// To make heading row's font bold
		final var style = workBook.createCellStyle();
		final var font = workBook.createFont();
		font.setBold(true);
		style.setFont(font);

		// Writing heading cell names in inital-row
		for (int column = 0; column < columnNames.size(); column++) {
			final var cell = initialRow.createCell(column);
			cell.setCellStyle(style);
			cell.setCellValue(columnNames.get(column));
		}
	}

	private void autoSizeColumns(final XSSFSheet workSheet) {
		for (int column = 0; column < columnNames.size(); column++) {
			workSheet.autoSizeColumn(column);
		}
	}

	private void writeSuperHeroDataInSheet(final XSSFSheet workSheet, final List<SuperHero> superHeroes) {
		for (int row = 0; row < superHeroes.size(); row++) {

			// making a new row representing the current employee
			final var currentRow = workSheet.createRow(row + 1);

			// populating current employees data in columns
			for (int column = 0; column < columnNames.size(); column++) {
				final var currentCell = currentRow.createCell(column);
				final var currentSuperHero = superHeroes.get(row);
				currentCell.setCellValue(ExcelCellUtils.getValue(column, currentSuperHero));
			}
		}
	}
}
