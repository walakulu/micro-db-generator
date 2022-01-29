package com.microdb.generator.client;

import com.microdb.generator.model.modelValidation.SuspiciousTransactionGroupModel;
import com.microdb.generator.model.modelValidation.TransactionModel;

import java.util.List;

public interface DatabaseClient {

	int executeDDL(String databaseName, String ddlStatement);

	List<SuspiciousTransactionGroupModel> findUnusualPaymentsForGoodOrService(String databaseName, String today,
			String groupDays);

	List<TransactionModel> findTransactionByReceiverAccountId(String databaseName, int origAccId, int beneAccId, String txnType,
															  String today, String groupDays);

	List<TransactionModel> findTransactionByReceiverAccountId(String databaseName, int beneAccId, String txnType,
															  String today, String groupDays);

    List<SuspiciousTransactionGroupModel> findUnusualPaymentReceive(String databaseName, String startDate, String groupDays);

	List<SuspiciousTransactionGroupModel> findUnusualSmallFeeTransfer(String databaseName, String startDate, String twoWeekGroup);

	List<TransactionModel> findTransactionBySenderAccountId(String databaseName, int origAccId, String txnType, String today, String groupDays);
}
