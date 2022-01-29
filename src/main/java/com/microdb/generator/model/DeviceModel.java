package com.microdb.generator.model;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class DeviceModel {

	private String deviceName;

	public String getDeviceName() {
		return deviceName;
	}

	private DeviceModel(Builder builder) {
		this.deviceName = builder.deviceName;
	}

	public static class Builder {

		private String deviceName;

		public Builder withDeviceName(String deviceName) {
			this.deviceName = deviceName;
			return this;
		}

		public DeviceModel build() {
			return new DeviceModel(this);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DeviceModel that = (DeviceModel) o;
		return Objects.equals(deviceName, that.deviceName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(deviceName);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("deviceName", deviceName).toString();
	}

}
