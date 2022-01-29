package com.microdb.generator.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = TableIndex.Builder.class)
public class TableIndex {

	private String indexType;

	private String indexName;

	private String columnName;

	TableIndex(Builder builder) {
		this.indexType = builder.indexType;
		this.indexName = builder.indexName;
		this.columnName = builder.columnName;
	}

	public String getIndexType() {
		return indexType;
	}

	public String getIndexName() {
		return indexName;
	}

	public String getColumnName() {
		return columnName;
	}

	public static class Builder {

		private String indexType;

		private String indexName;

		private String columnName;

		public Builder withIndexType(String indexType) {
			this.indexType = indexType;
			return this;
		}

		public Builder withIndexName(String indexName) {
			this.indexName = indexName;
			return this;
		}

		public Builder withColumnName(String columnName) {
			this.columnName = columnName;
			return this;
		}

		public TableIndex build() {
			return new TableIndex(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

}
