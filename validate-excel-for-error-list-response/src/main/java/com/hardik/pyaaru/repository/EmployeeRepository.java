package com.hardik.pyaaru.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardik.pyaaru.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

	boolean existsByEmailId(String cellValue);

}
