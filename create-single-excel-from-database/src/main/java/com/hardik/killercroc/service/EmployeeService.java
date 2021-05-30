package com.hardik.killercroc.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hardik.killercroc.dto.EmployeeCreationRequestDto;
import com.hardik.killercroc.entity.Employee;
import com.hardik.killercroc.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public ResponseEntity<?> createEmployee(final EmployeeCreationRequestDto employeeCreationRequestDto) {

		if (employeeRepository.existsByEmailId(employeeCreationRequestDto.getEmailId()))
			return ResponseEntity.badRequest().body("Duplicate Email-id provided");

		final var employee = new Employee();
		employee.setActive(employeeCreationRequestDto.isActive());
		employee.setDateOfBirth(employeeCreationRequestDto.getDateOfBirth());
		employee.setEmailId(employeeCreationRequestDto.getEmailId());
		employee.setFirstName(employeeCreationRequestDto.getFirstName());
		employee.setJobTitle(employeeCreationRequestDto.getJobTitle());
		employee.setJoiningDate(employeeCreationRequestDto.getJoiningDate());
		employee.setLastName(employeeCreationRequestDto.getLastName());
		employee.setMiddleName(employeeCreationRequestDto.getMiddleName());

		employeeRepository.save(employee);
		return ResponseEntity.ok("Employee Saved Successfully");
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

}
