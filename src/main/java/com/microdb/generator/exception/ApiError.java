package com.microdb.generator.exception;

public enum ApiError {

	SCHEMA_PROPERTY_VALIDATION_FAILURE("CER-101", "Schema Property Validation Failure"), BAD_REQUEST("CER-105",
			"Bad Request");

	private final String code;

	private final String message;

	ApiError(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

}
