package com.hardik.kofta.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "com.hardik.kofta")
public class ExcelCacheConfigurationProperties {

	private OTP otp = new OTP();

	@Data
	public class OTP {
		private Integer expirationMinutes;
	}

}