package com.vinayak.bank_solution_project.dto;

public class Beneficiary {

	private Integer accountID;
	private Integer beneficairyAccountID;
	private String beneficairyIFSCCode;
	private String beneficairyName;
	private String status;

	public Beneficiary(Integer accountID, Integer beneficairyAccountID, String beneficairyIFSCCode,
			String beneficairyName, String status) {
		super();
		this.accountID = accountID;
		this.beneficairyAccountID = beneficairyAccountID;
		this.beneficairyIFSCCode = beneficairyIFSCCode;
		this.beneficairyName = beneficairyName;
		this.status = status;
	}

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public Integer getBeneficairyAccountID() {
		return beneficairyAccountID;
	}

	public void setBeneficairyAccountID(Integer beneficairyAccountID) {
		this.beneficairyAccountID = beneficairyAccountID;
	}

	public String getBeneficairyIFSCCode() {
		return beneficairyIFSCCode;
	}

	public void setBeneficairyIFSCCode(String beneficairyIFSCCode) {
		this.beneficairyIFSCCode = beneficairyIFSCCode;
	}

	public String getBeneficairyName() {
		return beneficairyName;
	}

	public void setBeneficairyName(String beneficairyName) {
		this.beneficairyName = beneficairyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Beneficiary [accountID=" + accountID + ", beneficairyAccountID=" + beneficairyAccountID
				+ ", beneficairyIFSCCode=" + beneficairyIFSCCode + ", beneficairyName=" + beneficairyName + ", status="
				+ status + "]";
	}
}