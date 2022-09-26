package com.vinayak.bank_solution_project.dto;

public class AccountBalance {

	private Integer accountID;
	private Integer balance;

	public AccountBalance(Integer accountID, Integer balance) {
		super();
		this.accountID = accountID;
		this.balance = balance;
	}

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountBalance [accountID=" + accountID + ", balance=" + balance + "]";
	}
}