package com.microdb.generator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@JsonDeserialize(builder = DatabaseTable.Builder.class)
public class DatabaseTable {

	private String tableName;

	private String tableType;

	private boolean isMainTable;

	private List<TableColumn> columns;

	private List<TableIndex> indexes;

	private DatabaseTable(Builder builder) {
		this.tableName = builder.tableName;
		this.tableType = builder.tableType;
		this.isMainTable = builder.isMainTable;
		this.columns = builder.columns;
		this.indexes = builder.indexes;
	}

	public String getTableName() {
		return tableName;
	}

	public String getTableType() {
		return tableType;
	}

	public boolean isMainTable() {
		return isMainTable;
	}

	public List<TableColumn> getColumns() {
		return columns;
	}

	public List<TableIndex> getIndexes() {
		return indexes;
	}

	public static class Builder {

		private String tableName;

		private String tableType;

		private boolean isMainTable;

		private List<TableColumn> columns;

		@JsonProperty(required = true)
		private List<TableIndex> indexes;

		public Builder withTableName(String tableName) {
			this.tableName = tableName;
			return this;
		}

		public Builder withTableType(String tableType) {
			this.tableType = tableType;
			return this;
		}

		public Builder withIsMainTable(boolean isMainTable) {
			this.isMainTable = isMainTable;
			return this;
		}

		public Builder withColumns(List<TableColumn> columns) {
			this.columns = columns;
			return this;
		}

		@JsonProperty(required = true)
		public Builder withIndexes(List<TableIndex> indexes) {
			this.indexes = indexes;
			return this;
		}

		public DatabaseTable build() {
			return new DatabaseTable(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

	public Builder cloneBuilder() {
		return builder().withTableName(tableName).withTableType(tableType).withIsMainTable(isMainTable)
				.withColumns(columns);
	}

}
