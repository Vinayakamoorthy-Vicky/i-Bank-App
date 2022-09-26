package com.vinayak.bank_solution_project.dto;

import java.util.Date;

public class Transactions {

	private Integer id;
	private Integer accountID;
	private Date date;
	private String type;
	private Integer amount;
	private String status;
	private String remarks;

	public Transactions(Integer id, Integer accountID, Date date, String type, Integer amount, String status,
			String remarks) {
		super();
		this.id = id;
		this.accountID = accountID;
		this.date = date;
		this.type = type;
		this.amount = amount;
		this.status = status;
		this.remarks = remarks;
	}

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Transactions [id=" + id + ", accountID=" + accountID + ", date=" + date + ", type=" + type + ", amount="
				+ amount + ", status=" + status + ", remarks=" + remarks + "]";
	}
}