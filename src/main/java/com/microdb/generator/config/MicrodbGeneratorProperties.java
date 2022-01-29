package com.microdb.generator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Optional;

@Validated
@Configuration
@ConfigurationProperties
public class MicrodbGeneratorProperties {

	@Autowired
	private Environment environment;

	@NotBlank
	@Value("${micro-db-generator.dbServer.host}")
	private String dbServerHost;

	@NotBlank
	@Value("${micro-db-generator.dbServer.port}")
	private String dbServerPort;

	@NotBlank
	@Value("${micro-db-generator.dbServer.user}")
	private String dbServerUser;

	@NotBlank
	@Value("${micro-db-generator.dbServer.password}")
	private String dbServerPassword;

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public String getDbServerHost() {
		return dbServerHost;
	}

	public void setDbServerHost(String dbServerHost) {
		this.dbServerHost = dbServerHost;
	}

	public String getDbServerPort() {
		return dbServerPort;
	}

	public void setDbServerPort(String dbServerPort) {
		this.dbServerPort = dbServerPort;
	}

	public String getDbServerUser() {
		return dbServerUser;
	}

	public void setDbServerUser(String dbServerUser) {
		this.dbServerUser = dbServerUser;
	}

	public String getDbServerPassword() {
		return dbServerPassword;
	}

	public void setDbServerPassword(String dbServerPassword) {
		this.dbServerPassword = dbServerPassword;
	}

	@PostConstruct
	public Optional<String> getActiveProfile() {
		return Arrays.stream(environment.getActiveProfiles()).findAny();
	}

}
