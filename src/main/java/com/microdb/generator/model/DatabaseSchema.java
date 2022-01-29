package com.microdb.generator.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(builder = DatabaseSchema.Builder.class)
public class DatabaseSchema {

	private List<DatabaseTable> tables;

	public List<DatabaseTable> getTables() {
		return tables;
	}

	private DatabaseSchema(Builder builder) {
		this.tables = builder.tables;
	}

	public static class Builder {

		private List<DatabaseTable> tables;

		public Builder withTables(List<DatabaseTable> tables) {
			this.tables = tables;
			return this;
		}

		public DatabaseSchema build() {
			return new DatabaseSchema(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

}
