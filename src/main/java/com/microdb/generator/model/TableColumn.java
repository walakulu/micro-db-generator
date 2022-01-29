package com.microdb.generator.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = TableColumn.Builder.class)
public class TableColumn {

	private String columnName;

	private boolean isNullable;

	private String dataType;

	TableColumn(Builder builder) {
		this.columnName = builder.columnName;
		this.isNullable = builder.isNullable;
		this.dataType = builder.dataType;
	}

	public String getColumnName() {
		return columnName;
	}

	public boolean isNullable() {
		return isNullable;
	}

	public String getDataType() {
		return dataType;
	}

	public static class Builder {

		private String columnName;

		private boolean isNullable;

		private String dataType;

		public Builder withColumnName(String columnName) {
			this.columnName = columnName;
			return this;
		}

		public Builder withIsNullable(boolean isNullable) {
			this.isNullable = isNullable;
			return this;
		}

		public Builder withDataType(String dataType) {
			this.dataType = dataType;
			return this;
		}

		public TableColumn build() {
			return new TableColumn(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

}
