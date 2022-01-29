package com.microdb.generator.dto.modelValidation;

import java.util.List;

public class SuspiciousReportDto {

	private final String dateFrom;

	private final String dateTo;

	private final List<SuspiciousDto> suspicions;

	private SuspiciousReportDto(Builder builder) {
		this.dateFrom = builder.dateFrom;
		this.dateTo = builder.dateTo;
		this.suspicions = builder.suspicions;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public List<SuspiciousDto> getSuspicions() {
		return suspicions;
	}

	public static class Builder {

		private String dateFrom;

		private String dateTo;

		private List<SuspiciousDto> suspicions;

		public Builder withDateFrom(String dateFrom) {
			this.dateFrom = dateFrom;
			return this;
		}

		public Builder withDateTo(String dateTo) {
			this.dateTo = dateTo;
			return this;
		}

		public Builder withSuspicions(List<SuspiciousDto> suspicions) {
			this.suspicions = suspicions;
			return this;
		}

		public SuspiciousReportDto build() {
			return new SuspiciousReportDto(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

}
