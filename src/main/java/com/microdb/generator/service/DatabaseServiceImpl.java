package com.microdb.generator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.microdb.generator.exception.MissingPrimaryKeyException;
import com.microdb.generator.mapper.TemporalAttributeMapper;
import com.microdb.generator.model.DatabaseSchema;
import com.microdb.generator.model.DatabaseTable;
import com.microdb.generator.model.TableColumn;
import com.microdb.generator.model.TableIndex;
import com.microdb.generator.service.template.DatabaseEngine;
import com.microdb.generator.service.template.MicroDatabaseTemplate;
import com.microdb.generator.service.template.MicroDatabaseTemplateFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DatabaseServiceImpl implements DatabaseService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseServiceImpl.class);

	@Autowired
	ValidationService validationService;

	@Autowired
	MicroDatabaseTemplateFactory microDatabaseTemplateFactory;

	@Autowired
	FileService fileService;

	@Override
	public void createDatabase(String databaseName, int cacheTimePeriod) {
		File mappingFile = readSchemaMapping(databaseName);
		DatabaseSchema databaseSchema = constructRelationalModel(mappingFile);
		// make sure mapping is correct based on validation rules
		validateSchema(databaseSchema);
		// Template Pattern - API contract
		MicroDatabaseTemplate microDatabaseTemplate = microDatabaseTemplateFactory.getTemplate(DatabaseEngine.MYSQL);
		microDatabaseTemplate.createDatabase(databaseName, cacheTimePeriod, databaseSchema);
	}

	private File readSchemaMapping(String databaseName) {
		File mappingFile = fileService.readFileFromClasspath("schema_mapping.yaml");
		return mappingFile;
	}

	private DatabaseSchema constructRelationalModel(File mappingFile) {
		DatabaseSchema databaseSchema = null;
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.findAndRegisterModules();
		try {
			databaseSchema = mapper.readValue(mappingFile, DatabaseSchema.class);
		}
		catch (IOException e) {
			LOGGER.error("YAML to Object Deserialization Failure..!!", e);
			throw new IllegalStateException("YAML to Object Deserialization Failure..!!");
		}

		// add transaction tables primary key to other tables.Add additional temporal
		// columns
		databaseSchema = addTemporalAttributes(databaseSchema);

		return databaseSchema;
	}

	private DatabaseSchema addTemporalAttributes(DatabaseSchema databaseSchema) {

		databaseSchema = TemporalAttributeMapper.mapKeyColumn(databaseSchema);

		databaseSchema.getTables().stream().filter(table -> table.getTableType().equalsIgnoreCase("TEMPORAL"))
				.map(DatabaseTable::getColumns).map(TemporalAttributeMapper::mapTemporalColumns)
				.collect(Collectors.toList());
		return databaseSchema;

	}

	private void validateSchema(DatabaseSchema databaseSchema) {
		validationService.validateDatabaseSchema(databaseSchema);

	}

}
