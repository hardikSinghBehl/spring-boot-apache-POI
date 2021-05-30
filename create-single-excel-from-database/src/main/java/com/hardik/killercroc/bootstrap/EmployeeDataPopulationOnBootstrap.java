package com.hardik.killercroc.bootstrap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.hardik.killercroc.entity.Employee;
import com.hardik.killercroc.repository.EmployeeRepository;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Component
@AllArgsConstructor
public class EmployeeDataPopulationOnBootstrap {

	private final EmployeeRepository employeeRepository;

	@PostConstruct
	void populatingEmployeeDataInsideInMemoryDatabaseOnStartup() {
		final var employeeList = new ArrayList<Employee>();
		final var random = new Random();
		final var jobTitleList = List.of("Owner", "CEO", "COO", "Backend Developer", "Frontend Developer",
				"Database Admin", "Designer", "Human Resource Manager", "Project manager", "Android Developer",
				"Web Developer", "System Engineer");

		for (int i = 1; i < 300; i++) {
			final var employee = new Employee();
			employee.setActive(random.nextInt(2) == 0 ? true : false);
			employee.setDateOfBirth(LocalDate.of(random.ints(1, 1950, 2001).sum(), random.ints(1, 1, 13).sum(),
					random.ints(1, 1, 26).sum()));
			employee.setFirstName(RandomString.make(7));
			employee.setLastName(RandomString.make(7));
			employee.setMiddleName(random.nextInt(2) == 0 ? RandomString.make(10) : null);
			employee.setEmailId(employee.getFirstName() + "." + employee.getLastName() + "@thelattice.in");
			employee.setJoiningDate(LocalDate.of(random.ints(1, 1989, 2021).sum(), random.ints(1, 1, 13).sum(),
					random.ints(1, 1, 26).sum()));
			employee.setJobTitle(jobTitleList.stream().findAny().get());
			employeeList.add(employee);
		}

		employeeRepository.saveAll(employeeList);
	}

}
