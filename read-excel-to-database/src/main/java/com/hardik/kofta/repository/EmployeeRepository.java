package com.hardik.kofta.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardik.kofta.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

	boolean existsByEmailId(String emailId);

}
