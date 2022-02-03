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

	/**
	 * <p>
	 * Get the users who pay unusual frequency (given threshold) of payments for
	 * goods/service within a week period.
	 * </p>
	 *
	 * <p>
	 * This pattern is expected to run on weekly fashion.But to reduce OLTP load, pattern
	 * run on monthly basis and collect statistics by considering weekly timeframes.Which
	 * means, this will produce 1 report for each week and bundle them and return as
	 * monthly report.
	 * </p>
	 * @param databaseName name of the database
	 * @return Suspicious reports covering a month time frame ( total reports= 1st week
	 * report_ 2nd+..+4th week)
	 */
	@GetMapping("/databaseName/{databaseName}/unusualBuyers")
	public SuspiciousReportsDto findUnusualPaymentsForGoodOrService(@PathVariable String databaseName) {

		return ruleGroupService.findUnusualPaymentsForGoodOrService(databaseName);
	}

	/**
	 * <p>
	 * Get the users who receive unusual frequency (given threshold) of payments from same
	 * or different users for weekly time period.
	 * </p>
	 *
	 * <p>
	 * This pattern is expected to run on weekly fashion.But to reduce OLTP load, pattern
	 * run on monthly basis and collect statistics by considering weekly timeframes.Which
	 * means, this will produce 1 report for each week and bundle them and return as
	 * monthly report.
	 * </p>
	 * @param databaseName name of the database
	 * @return Suspicious reports covering a month time frame ( total reports= 1st week
	 * report_ 2nd+..+4th week)
	 */
	@GetMapping("/databaseName/{databaseName}/unusualReceivers")
	public SuspiciousReportsDto findUnusualPaymentsReceive(@PathVariable String databaseName) {

		return ruleGroupService.findUnusualPaymentsReceive(databaseName);
	}

	/**
	 * <p>
	 * Get the users who transfer unusual frequency (given threshold) of small amounts to
	 * same or different users.
	 * </p>
	 *
	 * <p>
	 * This pattern is expected to run on 14 day fashion.But to reduce OLTP load, pattern
	 * run on monthly basis and collect statistics by considering 2 week timeframes.Which
	 * means, this will produce 1 report for each 14 days and bundle them and return as
	 * monthly report.
	 * </p>
	 * @param databaseName name of the database
	 * @return Suspicious reports covering a month time frame ( total reports=report for
	 * 1st 14 day+ report for next 14 day)
	 */
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
