package com.microdb.generator.service;

import com.microdb.generator.model.DatabaseTable;
import com.microdb.generator.model.TableColumn;
import com.microdb.generator.model.TableIndex;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DDLGenerator {

	/* Generate create database DDL */
	public String createDatabaseDDL(String databaseName) {
		String createDatabase = "CREATE DATABASE IF NOT EXISTS " + databaseName + ";" + System.lineSeparator();
		String switchDatabase = "USE " + databaseName + ";" + System.lineSeparator();
		return createDatabase + switchDatabase;
	}

	public String createTableDDL(DatabaseTable databaseTable) {
		// java to sql mapping

		String tablePrefix = "CREATE TABLE IF NOT EXISTS ";
		String tableName = databaseTable.getTableName();
		String comment = "/*------------------DDL Script For " + tableName + " Table-----------------------*/"
				+ System.lineSeparator();
		List<TableColumn> tableColumns = databaseTable.getColumns();
		String sqlTableColumns = tableColumns.stream().map(this::toSqlColumnRow).collect(Collectors.joining(", "));

		// indexes are optional
		List<TableIndex> tableIndexes = null;
		if (ObjectUtils.isEmpty(databaseTable.getIndexes())) {
			tableIndexes = Collections.emptyList();
		}
		else {
			tableIndexes = databaseTable.getIndexes();
		}

		String sqlTableIndexes = tableIndexes.stream().map(this::toSqlIndexRow).collect(Collectors.joining(", "));

		String temporalTableSQL = comment + tablePrefix + tableName + System.lineSeparator() + "(" + sqlTableColumns
				+ "," + sqlTableIndexes + ");";

		return temporalTableSQL;
	}

	/* This will create mysql Event */
	// user need to provide ONLY the temporal table
	public String createEventDDL(String databaseName, int cacheTimePeriod, String tableName) {
		String eventDdl = new StringBuilder("CREATE EVENT IF NOT EXISTS ").append(databaseName).append("_")
				.append(tableName).append("_Event").append(System.lineSeparator()).append("ON SCHEDULE EVERY ")
				.append(cacheTimePeriod).append(" Day").append(System.lineSeparator()).append("DO ")
				.append(System.lineSeparator()).append("DELETE FROM ").append(tableName)
				.append(" WHERE valid_end_time < ").append("NOW();").append(System.lineSeparator()).toString();

		return eventDdl;
	}

	private String toSqlIndexRow(TableIndex tableIndex) {
		String indexSQL = tableIndex.getIndexType() + " " + tableIndex.getIndexName() + " ( "
				+ tableIndex.getColumnName() + ")" + System.lineSeparator();
		return indexSQL;
	}

	private String toSqlColumnRow(TableColumn tableColumn) {
		String columnSQL = tableColumn.getColumnName() + " " + tableColumn.getDataType() + " "
				+ (tableColumn.isNullable() ? "NULL" : "NOT NULL") + System.lineSeparator();
		return columnSQL;
	}

}
