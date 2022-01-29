package com.microdb.generator.dto.modelValidation;

import java.util.List;

public class SuspiciousReportsDto {

	private List<SuspiciousReportDto> suspiciousReports;

	private SuspiciousReportsDto(Builder builder) {
		this.suspiciousReports = builder.suspiciousReports;
	}

	public List<SuspiciousReportDto> getSuspiciousReports() {
		return suspiciousReports;
	}

	public static class Builder {

		private List<SuspiciousReportDto> suspiciousReports;

		public Builder withSuspiciousReports(List<SuspiciousReportDto> suspiciousReports) {
			this.suspiciousReports = suspiciousReports;
			return this;
		}

		public SuspiciousReportsDto build() {
			return new SuspiciousReportsDto(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

}
