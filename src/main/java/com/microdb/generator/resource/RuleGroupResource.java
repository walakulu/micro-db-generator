package com.microdb.generator.resource;

import com.microdb.generator.dto.modelValidation.SuspiciousReportDto;
import com.microdb.generator.dto.modelValidation.SuspiciousReportsDto;
import com.microdb.generator.service.DatabaseService;
import com.microdb.generator.service.RuleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rules")
public class RuleGroupResource {

	@Autowired
	DatabaseService databaseService;

	@Autowired
	RuleGroupService ruleGroupService;

	@GetMapping("/databaseName/{databaseName}/unusualBuyers")
	public SuspiciousReportsDto findUnusualPaymentsForGoodOrService(@PathVariable String databaseName) {

		return ruleGroupService.findUnusualPaymentsForGoodOrService(databaseName);
	}

	@GetMapping("/databaseName/{databaseName}/unusualReceivers")
	public SuspiciousReportsDto findUnusualPaymentsReceive(@PathVariable String databaseName) {

		return ruleGroupService.findUnusualPaymentsReceive(databaseName);
	}

	@GetMapping("/databaseName/{databaseName}/unusualSmallAmountSenders")
	public SuspiciousReportsDto findUnusualSmallFeeTransfer(@PathVariable String databaseName) {

		return ruleGroupService.findUnusualSmallFeeTransfer(databaseName);
	}

	/**
	 * <p>
	 * This algorithm expected run on weekly fashion.It can identify suspicious records
	 * for a week. starting from today.
	 * </p>
	 * <p>
	 * Below are the algorithm steps:
	 * <ol>
	 * <li>Find accounts where CASH_IN fallowed by CASH_OUT ( same day or next day) for
	 * each day starting from today.</li>
	 * <li>Run algorithm for week and collect above records</li>
	 * <li>Find frequency of days which each account fallowed this pattern (Ex: 1st day 2,
	 * 2nd day 1.Total frequency =3)</li>
	 * <li>Evaluate weather this frequency exceed the threshold value given by domain
	 * expert.If then take as suspicious.</li>
	 * </ol>
	 * </p>
	 * @param databaseName name of the database
	 * @return suspiciousRecords for the week (excluding individual transaction details)
	 */
	@GetMapping("/databaseName/{databaseName}/cashInFallowedCashOutUsers")
	public SuspiciousReportDto findUnusualCashInFallowedCashOutUsers(@PathVariable String databaseName) {

		return ruleGroupService.findUnusualCashInFallowedCashOutUsers(databaseName);
	}

}
