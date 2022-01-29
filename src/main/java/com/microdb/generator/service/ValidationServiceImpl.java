package com.microdb.generator.service;

import com.microdb.generator.exception.SchemaPropertyValidationException;
import com.microdb.generator.model.DatabaseSchema;
import com.microdb.generator.model.DatabaseTable;
import com.microdb.generator.service.ValidationService;
import com.microdb.generator.service.validation.SchemaValidationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	List<SchemaValidationRule> schemaValidationRules;

	private void validateDatabaseTable(DatabaseTable databaseTable) {
		// level 1 attribute validation
		if (StringUtils.isEmpty(databaseTable.getTableName())) {
			throw new SchemaPropertyValidationException("tableName Cannot be empty..!");
		}
		if (StringUtils.isEmpty(databaseTable.getTableType())) {
			throw new SchemaPropertyValidationException("tableType Cannot be empty..!");
		}
		// level 2 attribute validation
		schemaValidationRules.stream()
				.filter(schemaValidationRule -> schemaValidationRule.getRuleEvaluationScope().equalsIgnoreCase("TABLE"))
				.forEach(rule -> rule.validate(databaseTable));
	}

	@Override
	public void validateDatabaseSchema(DatabaseSchema databaseSchema) {
		// schema object level validation
		if (ObjectUtils.isEmpty(databaseSchema.getTables())) {
			throw new SchemaPropertyValidationException("Schema validation failure.No table provided");
		}
		// table level validation
		databaseSchema.getTables().forEach(this::validateDatabaseTable);
	}

}
