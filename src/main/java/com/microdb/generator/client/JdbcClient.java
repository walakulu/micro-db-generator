package com.microdb.generator.client;

import com.microdb.generator.config.MicrodbGeneratorProperties;
import com.microdb.generator.model.modelValidation.SuspiciousTransactionGroupModel;
import com.microdb.generator.model.modelValidation.TransactionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcClient implements DatabaseClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcClient.class);

	@Autowired
	MicrodbGeneratorProperties properties;

	private Connection getDatabaseConnection(String databaseName) {
		String url = String.format("jdbc:mysql:%1$s:%2$s/%3$s", properties.getDbServerHost(),
				properties.getDbServerPort(), databaseName);
		String username = properties.getDbServerUser();
		String password = properties.getDbServerPassword();

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException exception) {
			throw new IllegalStateException("Database Connection Failure");
		}

		return conn;
	}

	@Override
	public int executeDDL(String databaseName, String ddlStatement) {

		String serverEndpoint = String.format("jdbc:mysql:%1$s:%2$s", properties.getDbServerHost(),
				properties.getDbServerPort());
		// Defines username and password to connect to main database server.
		String username = properties.getDbServerUser();
		String password = properties.getDbServerPassword();
		String url = serverEndpoint;

		// connect to actual database ONLY after we create the database.
		if (!ddlStatement.contains(databaseName) || ddlStatement.contains("EVENT")) {
			url = serverEndpoint + "/" + databaseName;
		}

		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(ddlStatement)) {

			stmt.execute();
			LOGGER.info("DDL successfully executed {}", ddlStatement);
		}
		catch (Exception e) {
			LOGGER.info("DDL Execution Failure : {}", ddlStatement);
			throw new IllegalStateException("DDL Execution Failure");
		}
		return 1;
	}

	@Override
	public List<SuspiciousTransactionGroupModel> findUnusualPaymentsForGoodOrService(String databaseName, String today,
			String groupDays) {
		List<SuspiciousTransactionGroupModel> suspiciousTransactionGroups = new ArrayList<>();
		String dateRange = "DATE_SUB('" + today + "',INTERVAL " + groupDays + ") AND '" + today + "' ";

		String find_unusual_payers = new StringBuilder(
				"SELECT txn.orig_acc, txn.bene_acc,count(*) as frequency,txnType.type as txnType ")
						.append("FROM Transaction txn ").append("INNER JOIN Transaction_Type txnType ")
						.append("ON txn.txn_type_id=txnType.id ").append("WHERE txn_timestamp BETWEEN ")
						.append(dateRange).append("GROUP BY txn.orig_acc,txn.bene_acc,txnType.type ")
						.append("HAVING COUNT(txn.orig_acc) > 6 ").append(" AND txnType.type='PAYMENT'").toString();

		try {
			Connection connection = getDatabaseConnection(databaseName);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(find_unusual_payers);
			while (rs.next()) {
				SuspiciousTransactionGroupModel suspiciousTransactionGroupModel = SuspiciousTransactionGroupModel
						.builder().withOrigAccId(rs.getInt("orig_acc")).withBeneAccId(rs.getInt("bene_acc"))
						.withFrequency(rs.getInt("frequency")).withTxnType(rs.getString("txnType")).build();
				suspiciousTransactionGroups.add(suspiciousTransactionGroupModel);

			}

		}
		catch (SQLException exception) {
			throw new IllegalStateException("SQL Execution Exception Issue!!");
		}

		return suspiciousTransactionGroups;
	}

	@Override
	public List<TransactionModel> findTransactionByReceiverAccountId(String databaseName, int origAccId, int beneAccId,
			String txnType, String today, String groupDays) {
		List<TransactionModel> transactionModels = new ArrayList<>();
		String dateRange = "DATE_SUB('" + today + "',INTERVAL " + groupDays + ") AND '" + today + "' ";

		String find_transactions = new StringBuilder(
				"SELECT txn.txn_id, txn.orig_acc, txn.bene_acc,txnType.type as txnType ")
						.append("FROM Transaction txn ").append("INNER JOIN Transaction_Type txnType ")
						.append("ON txn.txn_type_id=txnType.id ").append("WHERE txn_timestamp BETWEEN ")
						.append(dateRange).append("AND txn.orig_acc= ").append(origAccId).append(" AND txn.bene_acc=")
						.append(beneAccId).append(" AND txnType.type='PAYMENT'").toString();

		try {
			Connection connection = getDatabaseConnection(databaseName);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(find_transactions);
			while (rs.next()) {
				TransactionModel transactionModel = TransactionModel.builder().withTransactionId(rs.getInt("txn_id"))
						.withOriginatorAccountId(rs.getInt("orig_acc")).withBeneficiaryAccountId(rs.getInt("bene_acc"))
						.withTransactionType(rs.getString("txnType")).build();
				transactionModels.add(transactionModel);

			}

		}
		catch (SQLException exception) {
			throw new IllegalStateException("SQL Execution Exception Issue!!");
		}

		return transactionModels;
	}

	@Override
	public List<TransactionModel> findTransactionByReceiverAccountId(String databaseName, int beneAccId, String txnType,
			String today, String groupDays) {
		List<TransactionModel> transactionModels = new ArrayList<>();
		String dateRange = "DATE_SUB('" + today + "',INTERVAL " + groupDays + ") AND '" + today + "' ";

		String find_transactions = new StringBuilder(
				"SELECT txn.txn_id, txn.orig_acc, txn.bene_acc,txnType.type as txnType ")
						.append("FROM Transaction txn ").append("INNER JOIN Transaction_Type txnType ")
						.append("ON txn.txn_type_id=txnType.id ").append("WHERE txn_timestamp BETWEEN ")
						.append(dateRange).append(" AND txn.bene_acc=").append(beneAccId)
						.append(" AND txnType.type='TRANSFER'").toString();

		try {
			Connection connection = getDatabaseConnection(databaseName);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(find_transactions);
			while (rs.next()) {
				TransactionModel transactionModel = TransactionModel.builder().withTransactionId(rs.getInt("txn_id"))
						.withOriginatorAccountId(rs.getInt("orig_acc")).withBeneficiaryAccountId(rs.getInt("bene_acc"))
						.withTransactionType(rs.getString("txnType")).build();
				transactionModels.add(transactionModel);

			}

		}
		catch (SQLException exception) {
			throw new IllegalStateException("SQL Execution Exception Issue!!");
		}

		return transactionModels;
	}

	@Override
	public List<SuspiciousTransactionGroupModel> findUnusualPaymentReceive(String databaseName, String startDate,
			String groupDays) {
		List<SuspiciousTransactionGroupModel> suspiciousTransactionGroups = new ArrayList<>();
		String dateRange = "DATE_SUB('" + startDate + "',INTERVAL " + groupDays + ") AND '" + startDate + "' ";

		String find_unusual_payers = new StringBuilder(
				"SELECT txn.bene_acc,count(*) as frequency,txnType.type as txnType ").append("FROM Transaction txn ")
						.append("INNER JOIN Transaction_Type txnType ").append("ON txn.txn_type_id=txnType.id ")
						.append("WHERE txn_timestamp BETWEEN ").append(dateRange)
						.append("GROUP BY txn.bene_acc,txnType.type ").append("HAVING COUNT(txn.bene_acc) > 10 ")
						.append(" AND txnType.type='TRANSFER'").toString();

		try {
			Connection connection = getDatabaseConnection(databaseName);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(find_unusual_payers);
			while (rs.next()) {
				SuspiciousTransactionGroupModel suspiciousTransactionGroupModel = SuspiciousTransactionGroupModel
						.builder().withBeneAccId(rs.getInt("bene_acc")).withFrequency(rs.getInt("frequency"))
						.withTxnType(rs.getString("txnType")).build();
				suspiciousTransactionGroups.add(suspiciousTransactionGroupModel);

			}

		}
		catch (SQLException exception) {
			throw new IllegalStateException("SQL Execution Exception Issue!!");
		}

		return suspiciousTransactionGroups;
	}

	@Override
	public List<SuspiciousTransactionGroupModel> findUnusualSmallFeeTransfer(String databaseName, String startDate,
			String twoWeekGroup) {
		List<SuspiciousTransactionGroupModel> suspiciousTransactionGroups = new ArrayList<>();
		String dateRange = "DATE_SUB('" + startDate + "',INTERVAL " + twoWeekGroup + ") AND '" + startDate + "' ";

		String find_unusual_payers = new StringBuilder(
				"SELECT txn.orig_acc,count(*) as frequency,txnType.type as txnType ").append("FROM Transaction txn ")
						.append("INNER JOIN Transaction_Type txnType ").append("ON txn.txn_type_id=txnType.id ")
						.append("WHERE txn_amt < 200 AND  txn_timestamp BETWEEN ").append(dateRange)
						.append("GROUP BY txn.orig_acc,txnType.type ").append("HAVING COUNT(txn.orig_acc) > 7 ")
						.append(" AND txnType.type='TRANSFER'").toString();

		try {
			Connection connection = getDatabaseConnection(databaseName);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(find_unusual_payers);
			while (rs.next()) {
				SuspiciousTransactionGroupModel suspiciousTransactionGroupModel = SuspiciousTransactionGroupModel
						.builder().withBeneAccId(rs.getInt("orig_acc")).withFrequency(rs.getInt("frequency"))
						.withTxnType(rs.getString("txnType")).build();
				suspiciousTransactionGroups.add(suspiciousTransactionGroupModel);

			}

		}
		catch (SQLException exception) {
			throw new IllegalStateException("SQL Execution Exception Issue!!");
		}

		return suspiciousTransactionGroups;
	}

	@Override
	public List<TransactionModel> findTransactionBySenderAccountId(String databaseName, int origAccId, String txnType,
			String today, String groupDays) {
		List<TransactionModel> transactionModels = new ArrayList<>();
		String dateRange = "DATE_SUB('" + today + "',INTERVAL " + groupDays + ") AND '" + today + "' ";

		String find_transactions = new StringBuilder(
				"SELECT txn.txn_id, txn.orig_acc, txn.bene_acc,txnType.type as txnType ")
						.append("FROM Transaction txn ").append("INNER JOIN Transaction_Type txnType ")
						.append("ON txn.txn_type_id=txnType.id ").append("WHERE txn_timestamp BETWEEN ")
						.append(dateRange).append(" AND txn.orig_acc=").append(origAccId)
						.append(" AND txnType.type='TRANSFER'").toString();

		try {
			Connection connection = getDatabaseConnection(databaseName);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(find_transactions);
			while (rs.next()) {
				TransactionModel transactionModel = TransactionModel.builder().withTransactionId(rs.getInt("txn_id"))
						.withOriginatorAccountId(rs.getInt("orig_acc")).withBeneficiaryAccountId(rs.getInt("bene_acc"))
						.withTransactionType(rs.getString("txnType")).build();
				transactionModels.add(transactionModel);

			}

		}
		catch (SQLException exception) {
			throw new IllegalStateException("SQL Execution Exception Issue!!");
		}

		return transactionModels;
	}

}
