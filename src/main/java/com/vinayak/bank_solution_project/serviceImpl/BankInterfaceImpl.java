package com.vinayak.bank_solution_project.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinayak.bank_solution_project.dao.BankDao;
import com.vinayak.bank_solution_project.dto.Account;
import com.vinayak.bank_solution_project.dto.AccountBalance;
import com.vinayak.bank_solution_project.dto.Beneficiary;
import com.vinayak.bank_solution_project.dto.Transactions;
import com.vinayak.bank_solution_project.service.BankInterface;

@Service
public class BankInterfaceImpl implements BankInterface {
	@Autowired
	private BankDao bankDao;

	/*
	 * To Add Beneficiary details
	 */
	public String addBeneficiary(Integer accountId, Integer beneAccountId, String beneIfscCode, String beneName) {
		boolean flag = false;
		try {
			List<Beneficiary> beneficiarylist = bankDao.getAllBeneficiary();
			for (Beneficiary benficiary : beneficiarylist) {
				if (benficiary.getAccountID().intValue() == accountId.intValue()
						&& benficiary.getBeneficairyAccountID().intValue() == beneAccountId.intValue()
						&& benficiary.getBeneficairyIFSCCode().equalsIgnoreCase(beneIfscCode)
						&& benficiary.getBeneficairyName().equalsIgnoreCase(beneName)) {
					flag = true;
					break;
				}
			}
			if (Boolean.FALSE.equals(flag)) {
				beneficiarylist.add(new Beneficiary(accountId, beneAccountId, beneIfscCode, beneName, ""));
			} else {
				return "Beneficiary Details are already existing!!!..";
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "Beneficiary Details has been added successfully!!!..";
	}

	/*
	 * To Update Beneficiary details
	 */
	public String updateBeneficiary(Integer accountId, Integer beneAccountId, String beneIfscCode, String beneName) {
		boolean flag = false;
		try {
			List<Beneficiary> beneficiarylist = bankDao.getAllBeneficiary();
			for (Beneficiary beneficiary : beneficiarylist) {
				if (beneficiary.getAccountID().intValue() == accountId.intValue()
						&& beneficiary.getBeneficairyAccountID().intValue() == beneAccountId.intValue()) {
					beneficiary.setAccountID(accountId);
					beneficiary.setBeneficairyAccountID(beneAccountId);
					if (beneIfscCode != null && !beneIfscCode.equals("")) {
						beneficiary.setBeneficairyIFSCCode(beneIfscCode);
					}
					if (beneName != null && !beneName.equals("")) {
						beneficiary.setBeneficairyName(beneName);
					}
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		if (Boolean.FALSE.equals(flag)) {
			return "Beneficiary Details not found !!!..";
		}
		return "Beneficiary Details has been updated successfully !!!..";
	}

	/*
	 * To Delete Beneficiary details
	 */
	public String deleteBeneficiary(Integer accountId, Integer beneAccountId, String beneName) {
		boolean flag = false;
		try {
			List<Beneficiary> beneficiarylist = bankDao.getAllBeneficiary();
			for (Beneficiary beneficiary : beneficiarylist) {
				if (beneficiary.getAccountID().intValue() == accountId.intValue()
						&& beneficiary.getBeneficairyAccountID().intValue() == beneAccountId.intValue()
						&& beneficiary.getBeneficairyName().equalsIgnoreCase(beneName)) {
					beneficiarylist.remove(beneficiary);
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		if (Boolean.FALSE.equals(flag)) {
			return "Beneficiary Details not found !!!..";
		}
		return "Beneficiary Details has been Deleted successfully !!!..";
	}

	/*
	 * To get all Transactions details
	 */
	public List<Transactions> getTransactions(Integer accountID) {
		List<Transactions> transactions = new ArrayList<Transactions>();
		try {
			List<Transactions> transactionList = bankDao.getAllTransaction();
			for (Transactions transaction : transactionList) {
				if (transaction.getAccountID().intValue() == accountID.intValue()) {
					transactions.add(transaction);
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return transactions;
	}

	/*
	 * To Update Account details
	 */
	public String updateAccountDetails(Integer accountId, String accountName, String phone, String email) {
		StringBuilder str = new StringBuilder();
		boolean flag = false;
		try {
			List<Account> accountList = bankDao.getAllAccountDetails();
			for (Account acc : accountList) {
				if (acc.getAccountID().intValue() == accountId.intValue()) {

					if (accountName != null && !accountName.equals("")) {
						if (accountName.length() <= 35) {
							acc.setAccountName(accountName);
						} else {
							str.append("Entered account Name is exceeding more than 35 characters");
							str.append("\n");

						}
					}

					if (phone != null && !phone.equals("")) {
						Pattern ptrn = Pattern.compile("^[6-9][0-9]{9}");
						Matcher match = ptrn.matcher(phone);
						if (match.find() && match.group().equals(phone)) {
							acc.setPhone(phone);
						} else {
							str.append("Entered mobile number is invalid");
							str.append("\n");
						}
					}

					if (email != null && !email.equals("")) {
						Pattern ptrn = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
						Matcher match = ptrn.matcher(email);
						if (match.find() && match.group().equals(email)) {
							acc.setEmail(email);
						} else {
							str.append("Entered Email is invalid");
							str.append("\n");
						}
					}
					flag = true;
					break;
				}
			}
			if (Boolean.FALSE.equals(flag)) {
				str.append("Account Details are not found!!!..");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		if (str.toString().isEmpty() || str.toString() == null || str.toString().equals("")) {
			str.append("Account Details has been updated successfully !!!..");
		}
		return str.toString();
	}

	/*
	 * To Update cash deposit details
	 */
	public String cashDepositDetails(Integer accountId, String accountName, String phone, Integer depositAmount,
			String remarks) {
		StringBuilder str = new StringBuilder();
		boolean flag = false;
		boolean balanceFlag = false;
		int balanceAmt = 0;
		try {
			if (depositAmount > 0) {
				List<Account> accountList = bankDao.getAllAccountDetails();
				for (Account acc : accountList) {
					if (acc.getAccountID().intValue() == accountId.intValue()
							&& acc.getAccountName().equalsIgnoreCase(accountName) && acc.getPhone().equals(phone)) {
						List<Transactions> transactionList = bankDao.getAllTransaction();
						try {
							transactionList.add(new Transactions(transactionList.size() + 1, accountId, new Date(),
									"Credit", depositAmount, "Success", remarks));

							List<AccountBalance> balanceList = bankDao.getAllBalanceList();
							for (AccountBalance balance : balanceList) {
								if (balance.getAccountID().intValue() == accountId.intValue()) {
									balance.setBalance(balance.getBalance() + depositAmount);
									balanceAmt = balance.getBalance();
									balanceFlag = true;
									break;
								}
							}

							if (Boolean.FALSE.equals(balanceFlag)) {
								balanceList.add(new AccountBalance(acc.getAccountID(), depositAmount));
							}

							str.append("Transaction Successfull: Amount " + depositAmount
									+ " RS has been deposited into your account " + accountId);
							str.append(" Account Balance: " + balanceAmt + " RS");

						} catch (Exception e) {
							transactionList.add(new Transactions(5, accountId, new Date(), "Credit", depositAmount,
									"Failed", remarks));
							str.append("Transaction failed: Amount " + depositAmount
									+ " RS hasn't deposited into your account " + accountId);
							e.getMessage();
						}
						flag = true;
						break;
					}
				}
			} else {
				str.append("Deposit Amount shouldn't be Zero rupee");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		if (Boolean.FALSE.equals(flag)
				&& (str.toString().isEmpty() || str.toString() == null || str.toString().equals(""))) {
			str.append("Account Details not found !!!..");
		}
		return str.toString();
	}

	/*
	 * To Update cash withdrawal details
	 */
	public String cashWithdrawalDetails(Integer accountId, String accountName, String phone, Integer withDrawalAmount,
			String remarks) {
		StringBuilder str = new StringBuilder();
		boolean flag = false;
		boolean balanceFlag = false;
		int balanceAmt = 0;
		try {
			if (withDrawalAmount > 0) {
				List<Account> accountList = bankDao.getAllAccountDetails();
				for (Account acc : accountList) {
					if (acc.getAccountID().intValue() == accountId.intValue()
							&& acc.getAccountName().equalsIgnoreCase(accountName) && acc.getPhone().equals(phone)) {
						List<Transactions> transactionList = bankDao.getAllTransaction();
						try {
							List<AccountBalance> balanceList = bankDao.getAllBalanceList();
							for (AccountBalance balance : balanceList) {
								if (balance.getAccountID().intValue() == accountId.intValue()
										&& withDrawalAmount <= balance.getBalance()) {
									balance.setBalance(balance.getBalance() - withDrawalAmount);
									balanceAmt = balance.getBalance();
									balanceFlag = true;
									break;
								}
							}

							if (Boolean.FALSE.equals(balanceFlag)) {
								str.append("You don't have sufficient balance!!!.. ");
								str.append("\n");
							} else {
								transactionList.add(new Transactions(transactionList.size() + 1, accountId, new Date(),
										"Debit", withDrawalAmount, "Success", remarks));
								str.append("Transaction Successfull: Amount " + withDrawalAmount
										+ " RS has been withdrawn from your account " + accountId);
								str.append("\n");
								str.append("Account Balance: " + balanceAmt + " RS");
							}

						} catch (Exception e) {
							transactionList.add(new Transactions(5, accountId, new Date(), "Debit", withDrawalAmount,
									"Failed", remarks));
							str.append("Transaction failed: Amount " + withDrawalAmount
									+ " RS hasn't withdraw from your account " + accountId);
							e.getMessage();
						}
						flag = true;
						break;
					}
				}
			} else {
				str.append("Withdrawal Amount shouldn't be Zero rupee or less");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		if (Boolean.FALSE.equals(flag)
				&& (str.toString().isEmpty() || str.toString() == null || str.toString().equals(""))) {
			str.append("Account Details not found !!!..");
		}
		return str.toString();
	}
}
