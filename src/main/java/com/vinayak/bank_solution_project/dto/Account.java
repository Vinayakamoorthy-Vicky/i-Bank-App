package com.vinayak.bank_solution_project.dto;

public class Account {

	private Integer accountID;
	private String accountName;
	private String phone;
	private String email;
	private String status;

	public Account(Integer accountID, String accountName, String phone, String email, String status) {
		super();
		this.accountID = accountID;
		this.accountName = accountName;
		this.phone = phone;
		this.email = email;
		this.status = status;
	}

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", accountName=" + accountName + ", phone=" + phone + ", email="
				+ email + ", status=" + status + "]";
	}
}