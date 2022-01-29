package com.microdb.generator.service.validation;

import com.microdb.generator.exception.SchemaPropertyValidationException;
import com.microdb.generator.model.DatabaseTable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class ColumnValidationRule implements SchemaValidationRule {

	private final String RULE_EVALUATION_SCOPE = "TABLE";

	@Override
	public String getRuleEvaluationScope() {
		return RULE_EVALUATION_SCOPE;
	}

	@Override
	public void validate(DatabaseTable databaseTable) {
		if (ObjectUtils.isEmpty(databaseTable.getColumns())) {
			throw new SchemaPropertyValidationException("tableColumns Cannot be empty..!");
		}

		databaseTable.getColumns().forEach(tableColumn -> {
			if (StringUtils.isEmpty(tableColumn.getColumnName()) || StringUtils.isEmpty(tableColumn.getDataType())) {
				throw new SchemaPropertyValidationException(
						"One or more  of columnName,dataType attribute missing ..!");
			}
		}

		);
	}

}
