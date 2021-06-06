package com.hardik.pyaaru.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "com.hardik.pyaaru")
public class OpenApiConfigurationProperties {

	private Swagger swagger = new Swagger();

	@Data
	public class Swagger {
		private String title;
		private String description;
		private String apiVersion;

		private Contact contact = new Contact();

		@Data
		public class Contact {
			private String email;
			private String name;
			private String url;
		}
	}

}