package com.microdb.generator.service.template;

import com.microdb.generator.client.DatabaseClient;
import com.microdb.generator.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MicroDatabaseTemplateFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(MicroDatabaseTemplateFactory.class);

	@Autowired
	FileService fileService;

	@Autowired
	DatabaseClient databaseClient;

	public MicroDatabaseTemplate getTemplate(DatabaseEngine databaseEngine) {

		switch (databaseEngine) {
		case MYSQL:
			return new MysqlMicroDatabaseTemplate(fileService, databaseClient);
		default:
			String errorMsg = databaseEngine + " Database Engine Not Supported";
			LOGGER.error(errorMsg);
			throw new IllegalStateException(errorMsg);
		}

	}

}
