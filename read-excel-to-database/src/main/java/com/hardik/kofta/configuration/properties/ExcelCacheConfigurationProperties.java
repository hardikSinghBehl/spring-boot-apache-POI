package com.hardik.kofta.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "com.hardik.kofta")
public class ExcelCacheConfigurationProperties {

	private Code code = new Code();

	@Data
	public class Code {
		private Integer expirationMinutes;
	}

}