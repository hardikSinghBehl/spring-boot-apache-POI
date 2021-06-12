package com.hardik.kofta.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hardik.kofta.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

}
