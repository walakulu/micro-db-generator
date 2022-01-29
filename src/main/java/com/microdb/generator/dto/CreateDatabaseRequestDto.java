package com.microdb.generator.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = CreateDatabaseRequestDto.Builder.class)
public class CreateDatabaseRequestDto {

	private final String databaseNamePrefix;

	private final int cacheTimePeriod;

	private CreateDatabaseRequestDto(Builder builder) {
		this.databaseNamePrefix = builder.databaseNamePrefix;
		this.cacheTimePeriod = builder.cacheTimePeriod;
	}

	public String getDatabaseNamePrefix() {
		return databaseNamePrefix;
	}

	public int getCacheTimePeriod() {
		return cacheTimePeriod;
	}

	public static class Builder {

		private String databaseNamePrefix;

		private int cacheTimePeriod;

		public Builder withDatabaseNamePrefix(String dbNamePrefix) {
			this.databaseNamePrefix = dbNamePrefix;
			return this;
		}

		public Builder withCacheTimePeriod(int cacheTimePeriod) {
			this.cacheTimePeriod = cacheTimePeriod;
			return this;
		}

		public CreateDatabaseRequestDto build() {
			return new CreateDatabaseRequestDto(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

}
