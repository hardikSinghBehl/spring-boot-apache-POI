package com.hardik.killercroc.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

	private static final long serialVersionUID = -9050953080264878033L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true, updatable = false)
	private UUID id;

	@Column(name = "emailId", nullable = false, unique = true, length = 50)
	private String emailId;

	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "middle_name", nullable = true, length = 50)
	private String middleName;

	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Column(name = "date_of_birth", nullable = true)
	private LocalDate dateOfBirth;

	@Column(name = "joining_date", nullable = false)
	private LocalDate joiningDate;

	@Column(name = "job_title", nullable = false)
	private String jobTitle;

	@Column(name = "is_active", nullable = false)
	private boolean active;

	@PrePersist
	void onCreate() {
		this.id = UUID.randomUUID();
	}

}
