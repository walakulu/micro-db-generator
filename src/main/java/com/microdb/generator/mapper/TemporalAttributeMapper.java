package com.microdb.generator.mapper;

import com.microdb.generator.exception.MissingPrimaryKeyException;
import com.microdb.generator.model.DatabaseSchema;
import com.microdb.generator.model.DatabaseTable;
import com.microdb.generator.model.TableColumn;
import com.microdb.generator.model.TableIndex;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TemporalAttributeMapper {

	public static DatabaseSchema mapKeyColumn(DatabaseSchema databaseSchema) {

		TableIndex primaryKeyIndex = databaseSchema.getTables().stream().filter(DatabaseTable::isMainTable)
				.map(DatabaseTable::getIndexes).flatMap(Collection::stream)
				.filter(tableIndex -> tableIndex.getIndexType().equalsIgnoreCase("PRIMARY KEY")).findFirst()
				.orElseThrow(() -> new MissingPrimaryKeyException("Primary Key Index Found For Main Table"));

		TableColumn primaryKeyColumn = databaseSchema.getTables().stream().filter(DatabaseTable::isMainTable)
				.map(DatabaseTable::getColumns).flatMap(Collection::stream)
				.filter(tableColumn -> tableColumn.getColumnName().equalsIgnoreCase(primaryKeyIndex.getColumnName()))
				.findFirst()
				.orElseThrow(() -> new MissingPrimaryKeyException("Primary Key Column Found For Main Table"));

		databaseSchema.getTables().stream()
				.filter(table -> (!table.isMainTable() && table.getTableType().equalsIgnoreCase("TEMPORAL")))
				.map(DatabaseTable::getColumns).map(tableColumns -> tableColumns.add(primaryKeyColumn))
				.collect(Collectors.toList());

		List<DatabaseTable> modifiedTables = databaseSchema.getTables().stream()
				.filter(table -> (!table.isMainTable() && table.getTableType().equalsIgnoreCase("TEMPORAL")))
				.map(table -> TemporalAttributeMapper.addOrCreateIndex(table, primaryKeyIndex))
				.collect(Collectors.toList());
		List<String> modifiedTableNames = modifiedTables.stream().map(DatabaseTable::getTableName)
				.collect(Collectors.toList());

		List<DatabaseTable> nonModifiedTables = databaseSchema.getTables().stream()
				.filter(table -> !modifiedTableNames.contains(table.getTableName())).collect(Collectors.toList());

		List<DatabaseTable> allTables = new ArrayList<>();
		allTables.addAll(modifiedTables);
		allTables.addAll(nonModifiedTables);

		return DatabaseSchema.builder().withTables(allTables).build();

	}

	/*
	 * Indexes are optional by schema definition.So we need to make sure this attribute is
	 * not null , before set , we need to create INDEX by refering primary key index
	 * details. Why not to use primary key? One transaction involves with 2 accounts.So we
	 * can't use tnx_primary key for other temporal databases other than transaction table
	 */
	private static DatabaseTable addOrCreateIndex(DatabaseTable databaseTable, TableIndex primaryKeyIndex) {
		TableIndex generalIndex = TableIndex.builder().withIndexName(primaryKeyIndex.getIndexName())
				.withIndexType("INDEX").withColumnName(primaryKeyIndex.getColumnName()).build();

		if (ObjectUtils.isEmpty(databaseTable.getIndexes())) {
			List<TableIndex> indexes = new ArrayList<>();
			indexes.add(generalIndex);
			databaseTable = databaseTable.cloneBuilder().withIndexes(indexes).build();

		}
		else {
			databaseTable.getIndexes().add(generalIndex);
		}
		return databaseTable;
	}

	/*
	 * Every table has at least 1 column.So ,we are safe to add new columns directly
	 * (without null check)
	 */
	public static List<TableColumn> mapTemporalColumns(List<TableColumn> tableColumns) {

		TableColumn validStartTime = TableColumn.builder().withColumnName("valid_start_time").withIsNullable(false)
				.withDataType("DATETIME").build();
		TableColumn validEndTime = TableColumn.builder().withColumnName("valid_end_time").withIsNullable(false)
				.withDataType("DATETIME").build();
		tableColumns.add(validStartTime);
		tableColumns.add(validEndTime);

		return tableColumns;
	}

}
