package com.microdb.generator.model.modelValidation;

public class TransactionModel {

	private final int transactionId;

	private final int originatorAccountId;

	private final int beneficiaryAccountId;

	private final String transactionType;

	public int getOriginatorAccountId() {
		return originatorAccountId;
	}

	public int getBeneficiaryAccountId() {
		return beneficiaryAccountId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public int getTransactionId() {
		return transactionId;
	}

	TransactionModel(Builder builder) {
		this.transactionId = builder.transactionId;
		this.originatorAccountId = builder.originatorAccountId;
		this.beneficiaryAccountId = builder.beneficiaryAccountId;
		this.transactionType = builder.transactionType;

	}

	public static class Builder {

		private int transactionId;

		private int originatorAccountId;

		private int beneficiaryAccountId;

		private String transactionType;

		public Builder withTransactionId(int transactionId) {
			this.transactionId = transactionId;
			return this;
		}

		public Builder withOriginatorAccountId(int originatorAccountId) {
			this.originatorAccountId = originatorAccountId;
			return this;
		}

		public Builder withBeneficiaryAccountId(int beneficiaryAccountId) {
			this.beneficiaryAccountId = beneficiaryAccountId;
			return this;
		}

		public Builder withTransactionType(String transactionType) {
			this.transactionType = transactionType;
			return this;
		}

		public TransactionModel build() {
			return new TransactionModel(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

}
