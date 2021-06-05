package com.hardik.bharta.excel.utility;

import com.hardik.bharta.entity.SuperHero;

public class ExcelCellUtils {

	public static String getValue(final int columnIndex, final SuperHero superHero) {
		switch (columnIndex) {
		case 0: {
			return superHero.getId().toString();
		}
		case 1: {
			return superHero.getName();
		}
		case 2: {
			return superHero.getAlterEgo();
		}
		case 3: {
			return superHero.getMasterComic().getName();
		}
		case 4: {
			return superHero.getCreatedAt().toString();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + columnIndex);
		}
	}

}
