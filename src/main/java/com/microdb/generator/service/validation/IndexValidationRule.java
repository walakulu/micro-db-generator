package com.microdb.generator.service.validation;

import com.microdb.generator.exception.SchemaPropertyValidationException;
import com.microdb.generator.model.DatabaseTable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Component
public class IndexValidationRule implements SchemaValidationRule {

	// public static final int PRIORITY=1;

	// value can be either table or constraint
	private final String RULE_EVALUATION_SCOPE = "TABLE";

	@Override
	public String getRuleEvaluationScope() {
		return RULE_EVALUATION_SCOPE;
	}

	@Override
	public void validate(DatabaseTable databaseTable) {
		if (!ObjectUtils.isEmpty(databaseTable.getIndexes())) {
			databaseTable.getIndexes().forEach(tableIndex -> {
				if (StringUtils.isEmpty(tableIndex.getColumnName()) || StringUtils.isEmpty(tableIndex.getColumnName())
						|| StringUtils.isEmpty(tableIndex.getIndexType())) {
					throw new SchemaPropertyValidationException(
							"One or more mandatory column attribute are missing..!");
				}
			});

		}

	}

}
