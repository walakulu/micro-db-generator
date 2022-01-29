package com.microdb.generator.service.template;

import com.microdb.generator.model.DatabaseSchema;
import com.microdb.generator.model.DatabaseTable;
import com.microdb.generator.service.DDLGenerator;
import com.microdb.generator.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MicroDatabaseTemplate {

	private static final Logger LOGGER = LoggerFactory.getLogger(MicroDatabaseTemplate.class);

	FileService fileService;

	MicroDatabaseTemplate(FileService fileService) {
		this.fileService = fileService;
	}

	// subclasses should not be able to override this template method (final)
	public final void createDatabase(String databaseName, int cacheTimePeriod, DatabaseSchema databaseSchema) {
		generateSchema(databaseName, databaseSchema);
		executeSchema(databaseName);
		List<String> tableNames = getTemporalTableNames(databaseSchema);
		scheduleCachePeriod(databaseName, cacheTimePeriod, tableNames);
		saveConfigs();
	}

	// Event triggers only need to schedule on temporal tables
	private List<String> getTemporalTableNames(DatabaseSchema databaseSchema) {
		return databaseSchema.getTables().stream().filter(table -> table.getTableType().equalsIgnoreCase("TEMPORAL"))
				.map(DatabaseTable::getTableName).collect(Collectors.toList());
	}

	/**
	 * <p>
	 * The main responsibility of this method to convert the OLTP to temporal mapping into
	 * target database schema. Current implementation work as default behavior.It converts
	 * temporal mapping to SQL DDL statements and save them in outputs directory .
	 * </p>
	 * <p>
	 * <b>Hint:</b> For NOSQL databases : Generate index documents or collection mappings
	 * based on underlying database engine.
	 * </p>
	 * @param databaseName temporal database name
	 * @param databaseSchema schema mapping object
	 */

	void generateSchema(String databaseName, DatabaseSchema databaseSchema) {
		String temporalSqlFilename = getTemporalFileName(databaseName);
		DDLGenerator ddlGenerator = new DDLGenerator();
		// create database and switch
		String createDatabaseSql = ddlGenerator.createDatabaseDDL(databaseName);
		fileService.saveToFile(temporalSqlFilename, createDatabaseSql);
		// create tables & indexes
		int tableCount = databaseSchema.getTables().stream().map(ddlGenerator::createTableDDL)
				.map(tableDDL -> fileService.saveToFile(temporalSqlFilename, tableDDL)).reduce(0, Integer::sum);
		LOGGER.info("Generated Table Count:{}", tableCount);
	}

	protected String getTemporalFileName(String databaseName) {
		String filePath = "outputs/";
		return filePath + databaseName + "_temporal_schema.sql";
	}

	/**
	 * <p>
	 * The main intention of this method to connect to database server and create database
	 * and implement the previously (@code generateSchema step) generated schema in it.
	 * </p>
	 * <p>
	 * <b>Hint:</b> For NOSQL databases : Create indexes or collection etc.
	 * </p>
	 * @param databaseName temporal database name
	 */
	abstract void executeSchema(String databaseName);

	/**
	 * <p>
	 * The main intention of this method to provide a mechanism to maintain data caching
	 * time period. Since the different database engine support different mechanisms,
	 * users need to provide their preference implementation.
	 * </p>
	 * <p>
	 * Ex:
	 * <ul>
	 * <li>Database Events in MySql</li>
	 * <li>Database triggers</li>
	 * <li>External crone triggers in AWS EventBridge</li>
	 * <li>NOSQL databases has TTL</li>
	 * </ul>
	 * </p>
	 * @param databaseName temporal database name
	 * @param cachePeriod cache time period in days
	 * @param tableNames temporal table mapping
	 */
	abstract void scheduleCachePeriod(String databaseName, int cachePeriod, List<String> tableNames);

	/**
	 * Save configurations in a file system or database for later use
	 */
	abstract void saveConfigs();

}
