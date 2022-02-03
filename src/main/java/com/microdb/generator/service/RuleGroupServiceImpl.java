package com.microdb.generator.service;

import com.microdb.generator.client.DatabaseClient;
import com.microdb.generator.dto.modelValidation.SuspiciousDto;
import com.microdb.generator.dto.modelValidation.SuspiciousReportDto;
import com.microdb.generator.dto.modelValidation.SuspiciousReportsDto;
import com.microdb.generator.dto.modelValidation.TransactionDto;
import com.microdb.generator.model.modelValidation.SuspiciousTransactionGroupModel;
import com.microdb.generator.model.modelValidation.TransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RuleGroupServiceImpl implements RuleGroupService {

	// Tnx date min:'2022-01-01 00:00:00' -
	// Txn date max: '2023-12-21 00:00:00'
	private static final String TODAY = "2023-12-21 00:00:00";

	/*
	 * Since mysql DATE_SUB with interval produce inclusive results, we need to provide 1
	 * day less than actual
	 */
	private static final String ONE_WEEK_GROUP = "6 DAY";

	private static final String TWO_WEEK_GROUP = "13 DAY";

	@Autowired
	DatabaseClient databaseClient;

	@Override
	public SuspiciousReportsDto findUnusualPaymentsForGoodOrService(String databaseName) {
		/*
		 * 1.Get accounts with same from to combination of transactions with type=PAYMENT
		 * for 1st 7 days (group BY) 2.Get transactions for each origin & destination with
		 * tnxType=Payment
		 */
		List<SuspiciousReportDto> weeklyReportList = new ArrayList<>();

		/* Suspicious Report for last 7 days (4th week) */
		LocalDateTime startDate = LocalDateTime.parse(TODAY, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String lastDayOf4thWeek = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String firstDayOf4thWeek = startDate.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		SuspiciousReportDto suspiciousReportFor4thWeek = findSuspiciousBuyersReportForWeek(databaseName,
				lastDayOf4thWeek, firstDayOf4thWeek);

		/* Suspicious Report for 3rd week */
		startDate = startDate.minusDays(7);
		String lastDayOf3rdWeek = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String firstDayOf3rdWeek = startDate.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		SuspiciousReportDto suspiciousReportFor3rdWeek = findSuspiciousBuyersReportForWeek(databaseName,
				lastDayOf3rdWeek, firstDayOf3rdWeek);

		/* Suspicious Report for 2nd week */
		startDate = startDate.minusDays(7);
		String lastDayOf2ndWeek = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String firstDayOf2ndWeek = startDate.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		SuspiciousReportDto suspiciousReportFor2ndWeek = findSuspiciousBuyersReportForWeek(databaseName,
				lastDayOf2ndWeek, firstDayOf2ndWeek);

		/* Suspicious Report for 1st week */
		startDate = startDate.minusDays(7);
		String firstDayOf1stWeek = startDate.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String lastDayOf1stWeek = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		SuspiciousReportDto suspiciousReportFor1stWeek = findSuspiciousBuyersReportForWeek(databaseName,
				lastDayOf1stWeek, firstDayOf1stWeek);

		weeklyReportList.add(suspiciousReportFor4thWeek);
		weeklyReportList.add(suspiciousReportFor3rdWeek);
		weeklyReportList.add(suspiciousReportFor2ndWeek);
		weeklyReportList.add(suspiciousReportFor1stWeek);

		return SuspiciousReportsDto.builder().withSuspiciousReports(weeklyReportList).build();
	}

	@Override
	public SuspiciousReportsDto findUnusualPaymentsReceive(String databaseName) {

		List<SuspiciousReportDto> weeklyReportList = new ArrayList<>();

		/* Suspicious Report for last 7 days (4th week) */
		LocalDateTime startDate = LocalDateTime.parse(TODAY, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String lastDayOf4thWeek = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String firstDayOf4thWeek = startDate.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		SuspiciousReportDto suspiciousReportFor4thWeek = findSuspiciousPaymentReceiveReportForWeek(databaseName,
				lastDayOf4thWeek, firstDayOf4thWeek);

		/* Suspicious Report for 3rd week */
		startDate = startDate.minusDays(7);
		String lastDayOf3rdWeek = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String firstDayOf3rdWeek = startDate.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		SuspiciousReportDto suspiciousReportFor3rdWeek = findSuspiciousPaymentReceiveReportForWeek(databaseName,
				lastDayOf3rdWeek, firstDayOf3rdWeek);

		/* Suspicious Report for 2nd week */
		startDate = startDate.minusDays(7);
		String lastDayOf2ndWeek = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String firstDayOf2ndWeek = startDate.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		SuspiciousReportDto suspiciousReportFor2ndWeek = findSuspiciousPaymentReceiveReportForWeek(databaseName,
				lastDayOf2ndWeek, firstDayOf2ndWeek);

		/* Suspicious Report for 1st week */
		startDate = startDate.minusDays(7);
		String firstDayOf1stWeek = startDate.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String lastDayOf1stWeek = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		SuspiciousReportDto suspiciousReportFor1stWeek = findSuspiciousPaymentReceiveReportForWeek(databaseName,
				lastDayOf1stWeek, firstDayOf1stWeek);

		weeklyReportList.add(suspiciousReportFor4thWeek);
		weeklyReportList.add(suspiciousReportFor3rdWeek);
		weeklyReportList.add(suspiciousReportFor2ndWeek);
		weeklyReportList.add(suspiciousReportFor1stWeek);

		return SuspiciousReportsDto.builder().withSuspiciousReports(weeklyReportList).build();
	}

	@Override
	public SuspiciousReportsDto findUnusualSmallFeeTransfer(String databaseName) {
		List<SuspiciousReportDto> weeklyReportList = new ArrayList<>();

		/* Suspicious Report for last 14 days (2 week) */
		LocalDateTime startDate = LocalDateTime.parse(TODAY, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String lastDayOf4thWeek = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String firstDayOf3rdWeek = startDate.minusDays(14).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		SuspiciousReportDto suspiciousReportForLast2Weeks = findUnusualSmallFeeTransferReportForTwoWeeks(databaseName,
				lastDayOf4thWeek, firstDayOf3rdWeek);

		/* Suspicious Report for 3rd week */
		startDate = startDate.minusDays(14);
		String lastDayOf2ndWeek = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String firstDayOf1stWeek = startDate.minusDays(14).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		SuspiciousReportDto suspiciousReportForFirst2dWeeks = findSuspiciousPaymentReceiveReportForWeek(databaseName,
				lastDayOf2ndWeek, firstDayOf1stWeek);

		weeklyReportList.add(suspiciousReportForLast2Weeks);
		weeklyReportList.add(suspiciousReportForFirst2dWeeks);

		return SuspiciousReportsDto.builder().withSuspiciousReports(weeklyReportList).build();
	}

	@Override
	// This rule is expected run in weekly fashion and supporting to run only for last
	// week.
	public SuspiciousReportDto findUnusualCashInFallowedCashOutUsers(String databaseName) {
		LocalDateTime today = LocalDateTime.parse(TODAY, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String startDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String endDate = today.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		int THRESHOLD_FREQUENCY = 1;

		Map<Integer, List<Integer>> weeklyResult = new HashMap<>();
		Set<Integer> uniqueAccounts = new HashSet<>();
		// Map<Integer,Integer> summaryFrequency=new HashMap<>();
		List<SuspiciousDto> suspiciousDtoList = new ArrayList<>();
		for (int i = 0; i <= 5; i++) {

			List<Integer> cashInfallowedByCashOutAccounts = databaseClient
					.findDailyCashInFallowedCashOutUsers(databaseName, startDate, i);
			weeklyResult.put(i, cashInfallowedByCashOutAccounts);
			uniqueAccounts.addAll(cashInfallowedByCashOutAccounts);
		}

		uniqueAccounts.forEach(acc -> {
			int totalFrequency = 0;
			for (int day : weeklyResult.keySet()) {
				totalFrequency += Collections.frequency(weeklyResult.get(day), acc);
			}
			// put account in to suspicious list if it exceed the threshold frequency
			// value for a week
			if (totalFrequency > THRESHOLD_FREQUENCY) {
				// summaryFrequency.put(acc,totalFrequency);
				// we are not attaching transactions to make logic simpler
				SuspiciousDto suspiciousDto = SuspiciousDto.builder().withAccountId(acc).withFrequency(totalFrequency)
						.build();
				suspiciousDtoList.add(suspiciousDto);
			}

		});

		return SuspiciousReportDto.builder().withDateFrom(startDate).withDateTo(endDate)
				.withSuspicions(suspiciousDtoList).build();
	}

	private SuspiciousReportDto findUnusualSmallFeeTransferReportForTwoWeeks(String databaseName, String startDate,
			String endDate) {
		List<SuspiciousTransactionGroupModel> suspiciousTransactionGroups = databaseClient
				.findUnusualSmallFeeTransfer(databaseName, startDate, TWO_WEEK_GROUP);

		List<SuspiciousDto> suspiciousDtos = suspiciousTransactionGroups.stream().map(groupModel -> {
			List<TransactionDto> transactions = getTransactionsBySenderAccountId(databaseName,
					groupModel.getBeneAccId(), groupModel.getTxnType(), startDate, TWO_WEEK_GROUP);
			SuspiciousDto suspiciousDto = SuspiciousDto.builder().withAccountId(groupModel.getBeneAccId())
					.withFrequency(groupModel.getFrequency()).withTransactions(transactions).build();

			return suspiciousDto;

		}

		).collect(Collectors.toList());
		SuspiciousReportDto suspiciousReportDto = SuspiciousReportDto.builder().withDateFrom(startDate)
				.withDateTo(endDate).withSuspicions(suspiciousDtos).build();

		return suspiciousReportDto;
	}

	private SuspiciousReportDto findSuspiciousPaymentReceiveReportForWeek(String databaseName, String startDate,
			String endDate) {
		List<SuspiciousTransactionGroupModel> suspiciousTransactionGroups = databaseClient
				.findUnusualPaymentReceive(databaseName, startDate, ONE_WEEK_GROUP);

		List<SuspiciousDto> suspiciousDtos = suspiciousTransactionGroups.stream().map(groupModel -> {
			List<TransactionDto> transactions = getTransactionsByReceiverAccountId(databaseName,
					groupModel.getBeneAccId(), groupModel.getTxnType(), startDate, ONE_WEEK_GROUP);
			SuspiciousDto suspiciousDto = SuspiciousDto.builder().withAccountId(groupModel.getBeneAccId())
					.withFrequency(groupModel.getFrequency()).withTransactions(transactions).build();

			return suspiciousDto;

		}

		).collect(Collectors.toList());
		SuspiciousReportDto suspiciousReportDto = SuspiciousReportDto.builder().withDateFrom(startDate)
				.withDateTo(endDate).withSuspicions(suspiciousDtos).build();

		return suspiciousReportDto;
	}

	private SuspiciousReportDto findSuspiciousBuyersReportForWeek(String databaseName, String startDate,
			String endDate) {
		List<SuspiciousTransactionGroupModel> suspiciousTransactionGroups = databaseClient
				.findUnusualPaymentsForGoodOrService(databaseName, startDate, ONE_WEEK_GROUP);

		List<SuspiciousDto> suspiciousDtos = suspiciousTransactionGroups.stream().map(groupModel -> {
			List<TransactionDto> transactions = getTransactionsByReceiverAccountId(databaseName,
					groupModel.getOrigAccId(), groupModel.getBeneAccId(), groupModel.getTxnType(), startDate,
					ONE_WEEK_GROUP);
			SuspiciousDto suspiciousDto = SuspiciousDto.builder().withAccountId(groupModel.getOrigAccId())
					.withFrequency(groupModel.getFrequency()).withTransactions(transactions).build();

			return suspiciousDto;

		}

		).collect(Collectors.toList());
		SuspiciousReportDto suspiciousReportDto = SuspiciousReportDto.builder().withDateFrom(startDate)
				.withDateTo(endDate).withSuspicions(suspiciousDtos).build();

		return suspiciousReportDto;
	}

	/*
	 * Find transactions by providing databaseName,origAccId,beneAccId,txnType,& time
	 * period
	 */
	private List<TransactionDto> getTransactionsByReceiverAccountId(String databaseName, int origAccId, int beneAccId,
			String txnType, String today, String groupDays) {
		List<TransactionModel> transactionModel = databaseClient.findTransactionByReceiverAccountId(databaseName,
				origAccId, beneAccId, txnType, today, groupDays);

		List<TransactionDto> transactionDtos = transactionModel.stream()
				.map(txnModel -> TransactionDto.builder().withTransactionId(txnModel.getTransactionId())
						.withOriginatorAccountId(txnModel.getOriginatorAccountId())
						.withBeneficiaryAccountId(txnModel.getBeneficiaryAccountId())
						.withTransactionType(txnModel.getTransactionType()).build())
				.collect(Collectors.toList());

		return transactionDtos;
	}

	/* Find transactions by providing databaseName,beneAccId,txnType,& time period */
	private List<TransactionDto> getTransactionsByReceiverAccountId(String databaseName, int beneAccId, String txnType,
			String today, String groupDays) {
		List<TransactionModel> transactionModel = databaseClient.findTransactionByReceiverAccountId(databaseName,
				beneAccId, txnType, today, groupDays);

		List<TransactionDto> transactionDtos = transactionModel.stream()
				.map(txnModel -> TransactionDto.builder().withTransactionId(txnModel.getTransactionId())
						.withOriginatorAccountId(txnModel.getOriginatorAccountId())
						.withBeneficiaryAccountId(txnModel.getBeneficiaryAccountId())
						.withTransactionType(txnModel.getTransactionType()).build())
				.collect(Collectors.toList());

		return transactionDtos;
	}

	private List<TransactionDto> getTransactionsBySenderAccountId(String databaseName, int origAccId, String txnType,
			String today, String groupDays) {
		List<TransactionModel> transactionModel = databaseClient.findTransactionBySenderAccountId(databaseName,
				origAccId, txnType, today, groupDays);

		List<TransactionDto> transactionDtos = transactionModel.stream()
				.map(txnModel -> TransactionDto.builder().withTransactionId(txnModel.getTransactionId())
						.withOriginatorAccountId(txnModel.getOriginatorAccountId())
						.withBeneficiaryAccountId(txnModel.getBeneficiaryAccountId())
						.withTransactionType(txnModel.getTransactionType()).build())
				.collect(Collectors.toList());

		return transactionDtos;
	}

}
