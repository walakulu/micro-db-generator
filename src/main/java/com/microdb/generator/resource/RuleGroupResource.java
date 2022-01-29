package com.microdb.generator.resource;

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

}
