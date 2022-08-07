package com.eazybytes.cards.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
public class Customer {

	private int customerId;

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + "]";
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
