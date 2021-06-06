package com.hardik.pyaaru.bootstrap;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.hardik.pyaaru.entity.Employee;
import com.hardik.pyaaru.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Component
@RequiredArgsConstructor
public class EmployeeDataPopulationOnBootstrap {

	private final EmployeeRepository employeeRepository;
	private final List<String> emailIds = List.of("hardik.behl7444@gmail.com", "hardikbehl5@gmail.com",
			"dummy@gmail.com");

	@PostConstruct
	void populateEmployeeData() {
		emailIds.forEach(emailId -> {
			final var employee = new Employee();
			employee.setEmailId(emailId);
			employee.setFullName(RandomString.make(4) + " " + RandomString.make(7));
			employee.setSalaryPerMonth(new Random().doubles(2, 1500.89, 78000.543).sum());
			employeeRepository.save(employee);
		});
	}

}
