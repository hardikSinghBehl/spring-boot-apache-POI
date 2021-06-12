package com.hardik.kofta.service;

import org.springframework.stereotype.Service;

import com.hardik.kofta.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

}
