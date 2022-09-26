package com.vinayak.bank_solution_project.service;

import java.util.List;

import com.vinayak.bank_solution_project.dto.Transactions;

public interface BankInterface {

	public String addBeneficiary(Integer accountId, Integer beneAccountId, String beneIfscCode, String beneName);

	public String updateBeneficiary(Integer accountId, Integer beneAccountId, String beneIfscCode, String beneNamea);

	public String deleteBeneficiary(Integer accountId, Integer beneAccountId, String beneName);

	public List<Transactions> getTransactions(Integer accountID);

	public String updateAccountDetails(Integer accountId, String accountName, String phone, String email);

	public String cashDepositDetails(Integer accountId, String accountName, String phone, Integer depositAmount, String remarks);

	public String cashWithdrawalDetails(Integer accountId, String accountName, String phone, Integer withDrawalAmount, String remarks);
}
