package com.vinayak.bank_solution_project;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vinayak.bank_solution_project.dao.BankDao;
import com.vinayak.bank_solution_project.dto.Account;
import com.vinayak.bank_solution_project.dto.AccountBalance;
import com.vinayak.bank_solution_project.dto.Beneficiary;
import com.vinayak.bank_solution_project.dto.Transactions;
import com.vinayak.bank_solution_project.serviceImpl.BankInterfaceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BankAppTest {
	@InjectMocks
	BankInterfaceImpl bankInterfaceImpl;
	@Mock
	BankDao bankDao;

	@Test
	public void testAddBeneficiaryAccount() {
		String msg = "Beneficiary Details has been added successfully!!!..";
		String response = bankInterfaceImpl.addBeneficiary(1, 1000, "HDFC0004827", "Vinayaka");
		assertEquals(msg, response); // Scenario 1

		List<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
		beneficiaryList.add(new Beneficiary(1, 123094355, "IFSC00001", "Sample1", "Active"));
		beneficiaryList.add(new Beneficiary(1, 444535466, "IFSC00002", "Sample2", "Inactive"));
		beneficiaryList.add(new Beneficiary(2, 75443554, "ISFC0003", "Sample3", "Active"));
		when(bankDao.getAllBeneficiary()).thenReturn(beneficiaryList);

		String msg1 = "Beneficiary Details are already existing!!!..";
		String response1 = bankInterfaceImpl.addBeneficiary(1, 123094355, "IFSC00001", "Sample1");
		assertEquals(msg1, response1); // Scenario 2

	}

	@Test
	public void testUpdateBeneficiary() {
		List<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
		beneficiaryList.add(new Beneficiary(1, 123094355, "IFSC00001", "Sample1", "Active"));
		beneficiaryList.add(new Beneficiary(1, 444535466, "IFSC00002", "Sample2", "Inactive"));
		beneficiaryList.add(new Beneficiary(2, 75443554, "ISFC0003", "Sample3", "Active"));
		when(bankDao.getAllBeneficiary()).thenReturn(beneficiaryList);

		String msg = "Beneficiary Details has been updated successfully !!!..";
		String response = bankInterfaceImpl.updateBeneficiary(1, 444535466, "IFSC0010", "Sample2");
		assertEquals(msg, response); // Scenario 1

		String msg1 = "Beneficiary Details not found !!!..";
		String response1 = bankInterfaceImpl.updateBeneficiary(3, 12345678, "IFSC0020", "Sample4");
		assertEquals(msg1, response1); // Scenario 2
	}

	@Test
	public void testDeleteBeneficiary() {
		List<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
		beneficiaryList.add(new Beneficiary(1, 123094355, "IFSC00001", "Sample1", "Active"));
		beneficiaryList.add(new Beneficiary(1, 444535466, "IFSC00002", "Sample2", "Inactive"));
		beneficiaryList.add(new Beneficiary(2, 75443554, "ISFC0003", "Sample3", "Active"));
		when(bankDao.getAllBeneficiary()).thenReturn(beneficiaryList);

		String msg = "Beneficiary Details has been Deleted successfully !!!..";
		String response = bankInterfaceImpl.deleteBeneficiary(2, 75443554, "Sample3");
		assertEquals(msg, response);// Scenario 1

		String msg1 = "Beneficiary Details not found !!!..";
		String response1 = bankInterfaceImpl.deleteBeneficiary(3, 12345678, "Vinayaka");
		assertEquals(msg1, response1);// Scenario 2
	}

	@Test
	public void testGetAllTransactions() {
		List<Transactions> transactionList = new ArrayList<Transactions>();
		transactionList.add(new Transactions(1, 1, new Date(), "Credit", 1000, "Success", ""));
		transactionList.add(new Transactions(2, 1, new Date(), "Debit", 500, "Failure", ""));
		transactionList.add(new Transactions(3, 2, new Date(), "Credit", 5000, "Success", ""));
		transactionList.add(new Transactions(4, 3, new Date(), "Debit", 1000, "Success", ""));
		when(bankDao.getAllTransaction()).thenReturn(transactionList);

		List<Transactions> response = bankInterfaceImpl.getTransactions(1);
		assertSame(2, response.size());// Scenario 1

		List<Transactions> response1 = bankInterfaceImpl.getTransactions(5);
		assertSame(0, response1.size());// Scenario 2
	}

	@Test
	public void testUpdateAccountDetails() {
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, "Vinod", "9874561230", "vinod@ibank.com", "Active"));
		accountList.add(new Account(2, "Hafeez", "8760713487", "hafeez@ibank.com", "Active"));
		accountList.add(new Account(3, "Gobi", "7448556990", "gobi@ibank.com", "Active"));
		when(bankDao.getAllAccountDetails()).thenReturn(accountList);

		String response = bankInterfaceImpl.updateAccountDetails(4, "Vinayak", "8760713487",
				"vinayakamoorthy95@gmail.com");
		assertEquals("Account Details are not found!!!..", response);// Scenario 1

		String response1 = bankInterfaceImpl.updateAccountDetails(1, "Raju Saravanan Vinayaka moorthy  @ Vicky",
				"8760713487", "vinayakamoorthy95@gmail.com");
		assertEquals("Entered account Name is exceeding more than 35 characters" + "\n", response1);// Scenario 2

		String response2 = bankInterfaceImpl.updateAccountDetails(1, "Vinayaka moorthy", "1234567890",
				"vinayakamoorthy95@gmail.com");
		assertEquals("Entered mobile number is invalid" + "\n", response2);// Scenario 3

		String response3 = bankInterfaceImpl.updateAccountDetails(1, "Vinayaka moorthy", "8760713487",
				"vinayakamoorthy95");
		assertEquals("Entered Email is invalid" + "\n", response3);// Scenario 4

		String response4 = bankInterfaceImpl.updateAccountDetails(1, "Raju Saravanan Vinayaka moorthy  @ Vicky",
				"1234567890", "vinayakamoorthy95");
		assertEquals("Entered account Name is exceeding more than 35 characters" + "\n"
				+ "Entered mobile number is invalid" + "\n" + "Entered Email is invalid" + "\n", response4);// Scenario
																											// 5

	}

	@Test
	public void testCashDepositDetails() {
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, "Vinod", "9874561230", "vinod@ibank.com", "Active"));
		accountList.add(new Account(2, "Hafeez", "8760713487", "hafeez@ibank.com", "Active"));
		accountList.add(new Account(3, "Gobi", "7448556990", "gobi@ibank.com", "Active"));
		when(bankDao.getAllAccountDetails()).thenReturn(accountList);

		List<Transactions> transactionList = new ArrayList<Transactions>();
		transactionList.add(new Transactions(1, 1, new Date(), "Credit", 1000, "Success", ""));
		transactionList.add(new Transactions(2, 1, new Date(), "Debit", 500, "Failure", ""));
		transactionList.add(new Transactions(3, 2, new Date(), "Credit", 5000, "Success", ""));
		transactionList.add(new Transactions(4, 3, new Date(), "Debit", 1000, "Success", ""));
		when(bankDao.getAllTransaction()).thenReturn(transactionList);

		List<AccountBalance> accountBalanceList = new ArrayList<AccountBalance>();
		accountBalanceList.add(new AccountBalance(1, 10000));
		accountBalanceList.add(new AccountBalance(2, 5000));
		accountBalanceList.add(new AccountBalance(3, 3000));
		when(bankDao.getAllBalanceList()).thenReturn(accountBalanceList);

		String response = bankInterfaceImpl.cashDepositDetails(1, "Vinod", "9874561230", 0, "Savings");
		assertEquals("Deposit Amount shouldn't be Zero rupee", response); // Scenario 1

		String response1 = bankInterfaceImpl.cashDepositDetails(4, "Vinayaka", "7333416540", 10000, "Savings");
		assertEquals("Account Details not found !!!..", response1); // Scenario 2

		String response2 = bankInterfaceImpl.cashDepositDetails(2, "Hafeez", "8760713487", 10000, "Savings");
		assertEquals(
				"Transaction Successfull: Amount 10000 RS has been deposited into your account 2 Account Balance: 15000 RS",
				response2); // Scenario 3
	}

	@Test
	public void testCashWithdrawalDetails() {
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, "Vinod", "9874561230", "vinod@ibank.com", "Active"));
		accountList.add(new Account(2, "Hafeez", "8760713487", "hafeez@ibank.com", "Active"));
		accountList.add(new Account(3, "Gobi", "7448556990", "gobi@ibank.com", "Active"));
		when(bankDao.getAllAccountDetails()).thenReturn(accountList);

		List<Transactions> transactionList = new ArrayList<Transactions>();
		transactionList.add(new Transactions(1, 1, new Date(), "Credit", 1000, "Success", ""));
		transactionList.add(new Transactions(2, 1, new Date(), "Debit", 500, "Failure", ""));
		transactionList.add(new Transactions(3, 2, new Date(), "Credit", 5000, "Success", ""));
		transactionList.add(new Transactions(4, 3, new Date(), "Debit", 1000, "Success", ""));
		when(bankDao.getAllTransaction()).thenReturn(transactionList);

		List<AccountBalance> accountBalanceList = new ArrayList<AccountBalance>();
		accountBalanceList.add(new AccountBalance(1, 10000));
		accountBalanceList.add(new AccountBalance(2, 5000));
		accountBalanceList.add(new AccountBalance(3, 3000));
		when(bankDao.getAllBalanceList()).thenReturn(accountBalanceList);

		String response = bankInterfaceImpl.cashWithdrawalDetails(1, "Vinod", "9874561230", 0, "Savings");
		assertEquals("Withdrawal Amount shouldn't be Zero rupee or less", response); // Scenario 1

		String response1 = bankInterfaceImpl.cashWithdrawalDetails(4, "Vinayaka", "7333416540", 10000, "Savings");
		assertEquals("Account Details not found !!!..", response1); // Scenario 2

		String response2 = bankInterfaceImpl.cashWithdrawalDetails(2, "Hafeez", "8760713487", 10000, "Savings");
		assertEquals("You don't have sufficient balance!!!.. " + "\n", response2); // Scenario 3

		String response3 = bankInterfaceImpl.cashWithdrawalDetails(2, "Hafeez", "8760713487", 1000, "Savings");
		assertEquals("Transaction Successfull: Amount 1000 RS has been withdrawn from your account 2" + "\n"
				+ "Account Balance: 4000 RS", response3); // Scenario 3
	}
}
