package com.vinayak.bank_solution_project.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vinayak.bank_solution_project.dto.Account;
import com.vinayak.bank_solution_project.dto.AccountBalance;
import com.vinayak.bank_solution_project.dto.Beneficiary;
import com.vinayak.bank_solution_project.dto.Transactions;

@Repository
public class BankDao {
	private static List<Transactions> transactionList = new ArrayList<Transactions>();
	private static List<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
	private static List<Account> accountList = new ArrayList<Account>();
	private static List<AccountBalance> accountBalanceList = new ArrayList<AccountBalance>();

	static {
		transactionList.add(new Transactions(1, 1, new Date(), "Credit", 1000, "Success", ""));
		transactionList.add(new Transactions(2, 1, new Date(), "Debit", 500, "Failure", ""));
		transactionList.add(new Transactions(3, 2, new Date(), "Credit", 5000, "Success", ""));
		transactionList.add(new Transactions(4, 3, new Date(), "Debit", 1000, "Success", ""));

		beneficiaryList.add(new Beneficiary(1, 123094355, "IFSC00001", "Sample1", "Active"));
		beneficiaryList.add(new Beneficiary(1, 444535466, "IFSC00002", "Sample2", "Inactive"));
		beneficiaryList.add(new Beneficiary(2, 75443554, "ISFC0003", "Sample3", "Active"));

		accountList.add(new Account(1, "Vinod", "9874561230", "vinod@ibank.com", "Active"));
		accountList.add(new Account(2, "Hafeez", "8760713487", "hafeez@ibank.com", "Active"));
		accountList.add(new Account(3, "Gobi", "7448556990", "gobi@ibank.com", "Active"));

		accountBalanceList.add(new AccountBalance(1, 10000));
		accountBalanceList.add(new AccountBalance(2, 5000));
		accountBalanceList.add(new AccountBalance(3, 3000));
	}

	public List<Transactions> getAllTransaction() {
		return transactionList;
	}

	public List<Beneficiary> getAllBeneficiary() {
		return beneficiaryList;
	}

	public List<Account> getAllAccountDetails() {
		return accountList;
	}

	public List<AccountBalance> getAllBalanceList() {
		return accountBalanceList;
	}
}
