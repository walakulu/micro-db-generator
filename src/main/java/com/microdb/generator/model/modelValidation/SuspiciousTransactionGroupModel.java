package com.microdb.generator.model.modelValidation;

public class SuspiciousTransactionGroupModel {

	private final int origAccId;

	private final int beneAccId;

	private final int frequency;

	private final String txnType;

	public int getOrigAccId() {
		return origAccId;
	}

	public int getBeneAccId() {
		return beneAccId;
	}

	public int getFrequency() {
		return frequency;
	}

	public String getTxnType() {
		return txnType;
	}

	SuspiciousTransactionGroupModel(Builder builder) {
		this.origAccId = builder.origAccId;
		this.beneAccId = builder.beneAccId;
		this.frequency = builder.frequency;
		this.txnType = builder.txnType;
	}

	public static class Builder {

		private int origAccId;

		private int beneAccId;

		private int frequency;

		private String txnType;

		public Builder withOrigAccId(int origAccId) {
			this.origAccId = origAccId;
			return this;
		}

		public Builder withBeneAccId(int beneAccId) {
			this.beneAccId = beneAccId;
			return this;
		}

		public Builder withFrequency(int frequency) {
			this.frequency = frequency;
			return this;
		}

		public Builder withTxnType(String txnType) {
			this.txnType = txnType;
			return this;
		}

		public SuspiciousTransactionGroupModel build() {
			return new SuspiciousTransactionGroupModel(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

}
