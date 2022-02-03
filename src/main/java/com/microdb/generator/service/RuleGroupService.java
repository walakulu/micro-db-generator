package com.microdb.generator.service;

import com.microdb.generator.dto.modelValidation.SuspiciousReportDto;
import com.microdb.generator.dto.modelValidation.SuspiciousReportsDto;

public interface RuleGroupService {

	SuspiciousReportsDto findUnusualPaymentsForGoodOrService(String databaseName);

	SuspiciousReportsDto findUnusualPaymentsReceive(String databaseName);

	SuspiciousReportsDto findUnusualSmallFeeTransfer(String databaseName);

	SuspiciousReportDto findUnusualCashInFallowedCashOutUsers(String databaseName);

}
