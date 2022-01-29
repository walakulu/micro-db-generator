package com.microdb.generator.service.template;

import com.microdb.generator.client.DatabaseClient;
import com.microdb.generator.service.DDLGenerator;
import com.microdb.generator.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MysqlMicroDatabaseTemplate extends MicroDatabaseTemplate {

	private static final Logger LOGGER = LoggerFactory.getLogger(MysqlMicroDatabaseTemplate.class);

	DatabaseClient databaseClient;

	MysqlMicroDatabaseTemplate(FileService fileService, DatabaseClient databaseClient) {
		super(fileService);
		this.databaseClient = databaseClient;
	}

	@Override
	void executeSchema(String databaseName) {

		// Since nosql databases do not have schema, we need to read SQL schema fro file.
		// So , NOSQL implementation can use their own implementations
		String sQLScript = fileService.readFileContent(super.getTemporalFileName(databaseName));
		String[] dDLStatementWithComments = sQLScript.split(";");
		List<Integer> successResults = Arrays.stream(dDLStatementWithComments).map(this::removeComments)
				.map(ddlStatement -> databaseClient.executeDDL(databaseName, ddlStatement))
				.collect(Collectors.toList());
		LOGGER.info("Num Of DDLs executed :{}", successResults.size());
	}

	/*
	 * Remove comments in SQL ,Since our sql client cannot identify.This required only
	 * when your client not support to pass SQL comments.
	 */
	private String removeComments(String ddlMixedWithComments) {
		int startIndex = ddlMixedWithComments.indexOf("/*");
		int endIndex = ddlMixedWithComments.indexOf("*/");
		String replacement = "";
		String toBeReplaced;
		String pureSQL;
		if (startIndex < endIndex) {
			toBeReplaced = ddlMixedWithComments.substring(startIndex, endIndex + 2);
			pureSQL = ddlMixedWithComments.replace(toBeReplaced, replacement);
		}
		else {
			pureSQL = ddlMixedWithComments;
		}
		return pureSQL;
	}

	@Override
	void scheduleCachePeriod(String databaseName, int cacheTimePeriod, List<String> tableNames) {

		String temporalOutputFilePath = super.getTemporalFileName(databaseName);
		String ddlFileName = temporalOutputFilePath.substring(0, temporalOutputFilePath.lastIndexOf("/")) + "/"
				+ databaseName + "_event_schedule.sql";

		/*
		 * check event scheduler is active run -> show processlist; if scheduler details
		 * are not shown, it is switch off.We need to run below command to activate that
		 * SET GLOBAL event_scheduler=ON to show older created events -> show events to
		 * delete event -> drop <eventName>
		 */
		DDLGenerator ddlGenerator = new DDLGenerator();
		List<Integer> resultCount = tableNames.stream()
				.map(tableName -> ddlGenerator.createEventDDL(databaseName, cacheTimePeriod, tableName))
				.map(eventDdl -> {
					fileService.saveToFile(ddlFileName, eventDdl);
					return eventDdl.replace(";", "");
				}).map(eventDdl -> databaseClient.executeDDL(databaseName, eventDdl)).collect(Collectors.toList());
		LOGGER.info("Num of successful event schedules:{}", resultCount.size());
	}

	@Override
	void saveConfigs() {

	}

}
