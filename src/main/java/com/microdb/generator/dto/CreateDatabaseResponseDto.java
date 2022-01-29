package com.microdb.generator.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class CreateDatabaseResponseDto {

	private final String databaseName;

	private final int cacheTimePeriod;

	private CreateDatabaseResponseDto(Builder builder) {
		this.databaseName = builder.databaseName;
		this.cacheTimePeriod = builder.cacheTimePeriod;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public int getCacheTimePeriod() {
		return cacheTimePeriod;
	}

	public static class Builder {

		private String databaseName;

		private int cacheTimePeriod;

		public Builder withDatabaseName(String dbName) {
			this.databaseName = dbName;
			return this;
		}

		public Builder withCacheTimePeriod(int cacheTimePeriod) {
			this.cacheTimePeriod = cacheTimePeriod;
			return this;
		}

		public CreateDatabaseResponseDto build() {
			return new CreateDatabaseResponseDto(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

}
