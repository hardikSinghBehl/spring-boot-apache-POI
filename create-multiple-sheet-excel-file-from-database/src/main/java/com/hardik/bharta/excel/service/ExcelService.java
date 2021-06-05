package com.hardik.bharta.excel.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hardik.bharta.repository.MasterComicRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExcelService {

	private final MasterComicRepository masterComicRepository;

	public ResponseEntity<InputStreamResource> generate() {
		return null;
	}

}
