package com.microdb.generator.service.validation;

import com.microdb.generator.model.DatabaseTable;

public interface SchemaValidationRule {

	String getRuleEvaluationScope();

	void validate(DatabaseTable databaseTable);

}
