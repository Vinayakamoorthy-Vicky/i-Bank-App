package com.vinayak.bank_solution_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinayak.bank_solution_project.dto.Transactions;
import com.vinayak.bank_solution_project.service.BankInterface;

@RestController
@RequestMapping(path = "/i-bank")
public class BankController {
	@Autowired
	private BankInterface bankInterface;

	/*
	 * To add the Beneficiary details
	 */
	@PostMapping("/addBeneficiary")
	public ResponseEntity<String> addBeneficiaryAccount(@RequestParam(required = true) Integer accountId,
			@RequestParam(required = true) Integer beneAccountId, @RequestParam(required = true) String beneIfscCode,
			@RequestParam(required = true) String beneName) {
		String msg = "";
		try {
			msg = bankInterface.addBeneficiary(accountId, beneAccountId, beneIfscCode, beneName);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	/*
	 * To update the Beneficiary details
	 */
	@PutMapping("/updateBeneficiary")
	public ResponseEntity<String> updateBeneficiaryAccount(@RequestParam(required = true) Integer accountId,
			@RequestParam(required = true) Integer beneAccountId, @RequestParam(required = false) String beneIfscCode,
			@RequestParam(required = false) String beneName) {

		if (accountId != null && beneAccountId != null && (beneIfscCode != null || beneName != null)) {

			String msg = "";
			try {
				msg = bankInterface.updateBeneficiary(accountId, beneAccountId, beneIfscCode, beneName);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
			}
			return new ResponseEntity<String>(msg, HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("Account ID, Beneficiary Account ID & update details are mandatory",
					HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * To delete the Beneficiary details
	 */
	@DeleteMapping("/deleteBeneficiary")
	public ResponseEntity<String> deleteBeneficiaryAccount(@RequestParam(required = true) Integer accountId,
			@RequestParam(required = true) Integer beneAccountId, @RequestParam(required = true) String beneName) {
		String msg = "";
		try {
			msg = bankInterface.deleteBeneficiary(accountId, beneAccountId, beneName);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	/*
	 * To get all the transaction details
	 */
	@GetMapping("/transactions")
	public ResponseEntity<List<Transactions>> getTransactionList(@RequestParam Integer accountID) {
		if (accountID == null || accountID <= 0) {
			return new ResponseEntity<List<Transactions>>(HttpStatus.BAD_REQUEST);
		}
		List<Transactions> transactionList = bankInterface.getTransactions(accountID);
		if (!transactionList.isEmpty()) {
			return new ResponseEntity<List<Transactions>>(transactionList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Transactions>>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * To update the Personal Account details
	 */
	@PutMapping("/updatePersonalDetails")
	public ResponseEntity<String> updatePersonalDetails(@RequestParam(required = true) Integer accountId,
			@RequestParam(required = false) String accountName, @RequestParam(required = false) String phone,
			@RequestParam(required = false) String email) {
		if (accountId != null && (accountName != null || phone != null || email != null)) {
			String msg = "";
			try {
				msg = bankInterface.updateAccountDetails(accountId, accountName, phone, email);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
			}
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Account ID & other updation details are mandatory",
					HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * To update the cash deposit details
	 */
	@PostMapping("/deposit")
	public ResponseEntity<String> cashDeposit(@RequestParam(required = true) Integer accountId,
			@RequestParam(required = true) String accountName, @RequestParam(required = true) String phone,
			@RequestParam(required = true) Integer depositAmount, @RequestParam(required = false) String remarks) {
		String msg = "";
		try {
			msg = bankInterface.cashDepositDetails(accountId, accountName, phone, depositAmount, remarks);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	/*
	 * To update the cash withdrawal details
	 */
	@GetMapping("/withdraw")
	public ResponseEntity<String> withdraw(@RequestParam(required = true) Integer accountId,
			@RequestParam(required = true) String accountName, @RequestParam(required = true) String phone,
			@RequestParam(required = true) Integer withDrawalAmount, @RequestParam(required = false) String remarks) {
		String msg = "";
		try {
			msg = bankInterface.cashWithdrawalDetails(accountId, accountName, phone, withDrawalAmount, remarks);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}
