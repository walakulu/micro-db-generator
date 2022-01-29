package com.microdb.generator.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorsDto {

	private List<ErrorDto> errors;

	private ErrorsDto(Builder builder) {
		this.errors = builder.errors;
	}

	public List<ErrorDto> getErrors() {
		return errors;
	}

	public static class Builder {

		private List<ErrorDto> errors = new ArrayList<>();

		public Builder withError(ErrorDto error) {
			errors.add(error);
			return this;
		}

		public Builder withErrors(List<ErrorDto> errors) {
			this.errors = errors;
			return this;
		}

		public ErrorsDto build() {
			return new ErrorsDto(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

}
