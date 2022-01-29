package com.microdb.generator.dto.modelValidation;

import java.util.List;

public class SuspiciousDto {

	private final int accountId;

	private final int frequency;

	List<TransactionDto> transactions;

	public int getAccountId() {
		return accountId;
	}

	public int getFrequency() {
		return frequency;
	}

	public List<TransactionDto> getTransactions() {
		return transactions;
	}

	SuspiciousDto(Builder builder) {
		this.accountId = builder.accountId;
		this.frequency = builder.frequency;
		this.transactions = builder.transactions;
	}

	public static class Builder {

		private int accountId;

		private int frequency;

		List<TransactionDto> transactions;

		public Builder withAccountId(int accountId) {
			this.accountId = accountId;
			return this;
		}

		public Builder withFrequency(int frequency) {
			this.frequency = frequency;
			return this;
		}

		public Builder withTransactions(List<TransactionDto> transactions) {
			this.transactions = transactions;
			return this;
		}

		public SuspiciousDto build() {
			return new SuspiciousDto(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

}
