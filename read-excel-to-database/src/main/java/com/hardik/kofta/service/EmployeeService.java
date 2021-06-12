package com.hardik.kofta.service;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.cache.LoadingCache;
import com.hardik.kofta.entity.Employee;
import com.hardik.kofta.excel.service.ExcelService;
import com.hardik.kofta.exception.FileNotSameThatWasValidatedException;
import com.hardik.kofta.exception.InvalidCodeException;
import com.hardik.kofta.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final ExcelService excelService;
	private final LoadingCache<Integer, String> loadingCache;

	public ResponseEntity<?> bulkUpload(final Integer code, final MultipartFile file)
			throws NumberFormatException, ExecutionException, NoSuchAlgorithmException, IOException {
		final var retreivedCode = loadingCache.get(code);
		final var response = new JSONObject();
		if (retreivedCode == null)
			throw new InvalidCodeException();

		final var fileMessageDigest = MessageDigest.getInstance("MD5");
		fileMessageDigest.update(file.getBytes());
		final var fileHash = DatatypeConverter.printHexBinary(fileMessageDigest.digest()).toUpperCase();

		if (!retreivedCode.equals(fileHash))
			throw new FileNotSameThatWasValidatedException();

		final var data = excelService.extractData(file);
		employeeRepository.saveAll(data);

		response.put("message", data.size() + " Employees data successfully saved to database");
		response.put("timestamp", LocalDateTime.now().toString());
		return ResponseEntity.ok(response.toString());
	}

	public ResponseEntity<List<Employee>> retreiveEmployees() {
		return ResponseEntity.ok(employeeRepository.findAll());
	}

}
