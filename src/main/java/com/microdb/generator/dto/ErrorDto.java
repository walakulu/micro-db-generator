package com.microdb.generator.dto;

public class ErrorDto {

	private String code;

	private String message;

	private ErrorDto(Builder builder) {
		this.code = builder.code;
		this.message = builder.message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static class Builder {

		private String code;

		private String message;

		public Builder withCode(String code) {
			this.code = code;
			return this;
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public ErrorDto build() {
			return new ErrorDto(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

}
