package com.hardik.killercroc.utility;

import com.hardik.killercroc.entity.Employee;

public class ExcelCellUtils {

	public static String getValue(final int columnIndex, final Employee employee) {
		switch (columnIndex) {
		case 0: {
			return employee.getId().toString();
		}
		case 1: {
			return employee.getEmailId();
		}
		case 2: {
			return employee.getDateOfBirth().toString();
		}
		case 3: {
			return employee.getFirstName();
		}
		case 4: {
			return employee.getMiddleName();
		}
		case 5: {
			return employee.getLastName();
		}
		case 6: {
			return employee.getJobTitle();
		}
		case 7: {
			return employee.getJoiningDate().toString();
		}
		case 8: {
			return employee.isActive() ? "true" : "false";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + columnIndex);
		}
	}

}
