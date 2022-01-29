package com.microdb.generator.service;

import com.microdb.generator.model.DatabaseSchema;

public interface ValidationService {

	void validateDatabaseSchema(DatabaseSchema databaseSchema);

}
