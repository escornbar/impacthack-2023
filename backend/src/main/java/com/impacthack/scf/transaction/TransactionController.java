package com.impacthack.scf.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.impacthack.scf.account.Account;
import com.impacthack.scf.account.AccountRepository;
import com.impacthack.scf.invoice.Invoice;
import com.impacthack.scf.invoice.InvoiceRepository;
import com.impacthack.scf.loan.Loan;
import com.impacthack.scf.loan.LoanRepository;

@RestController
@RequestMapping("/api")
public class TransactionController {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	LoanRepository loanRepository;

	@GetMapping("/transactions")
	@Transactional
	public ResponseEntity<List<Transaction>> getAllTransactions(@RequestParam(required = false) Long invoiceId) {

		try {
			List<Transaction> transactions = new ArrayList<Transaction>();
			List<Transaction> filteredTransactions = new ArrayList<Transaction>();

			Invoice invoice = null;

			if (invoiceId == null) {
				transactionRepository.findAll().forEach(filteredTransactions::add);
			} else {
				invoice = invoiceRepository.findById(invoiceId).orElse(null);
				transactionRepository.findByInvoice(invoice).forEach(transactions::add);

				// remove the same Invoice from json object returned
				for (int i = 0; i < transactions.size(); i++) {
					Transaction currTransaction = transactions.get(i);
					Transaction newTransaction = new Transaction(currTransaction.getTransactionId(),
							currTransaction.getTransactionDate(), currTransaction.getTransactionAmount(),
							currTransaction.getRefNo(), currTransaction.getFromAccount(),
							currTransaction.getToAccount(), currTransaction.getTransactionAmountAfterRepayment(),
							currTransaction.getRepaymentRate());
					filteredTransactions.add(newTransaction);
				}
			}

			if (filteredTransactions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(filteredTransactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/transactions/accounts/{accountNumber}")
	@Transactional
	public ResponseEntity<List<Transaction>> getTransactionsByAccount(
			@PathVariable("accountNumber") String accountNumber) {
		try {
			List<Transaction> transactions = new ArrayList<Transaction>();

			Account account = accountRepository.findByAccountNumber(accountNumber).orElse(null);
			transactionRepository.findByToAccount(account).forEach(transactions::add);
			transactionRepository.findByFromAccount(account).forEach(transactions::add);

			if (transactions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(transactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/transactions/accounts/from/{accountNumber}")
	@Transactional
	public ResponseEntity<List<Transaction>> getTransactionsFrom(@PathVariable("accountNumber") String accountNumber) {

		try {
			List<Transaction> transactions = new ArrayList<Transaction>();
			List<Transaction> filteredTransactions = new ArrayList<Transaction>();

			Account account = accountRepository.findByAccountNumber(accountNumber).orElse(null);
			transactionRepository.findByFromAccount(account).forEach(transactions::add);

			// remove the same Invoice from json object returned
			for (int i = 0; i < transactions.size(); i++) {
				Transaction currTransaction = transactions.get(i);
				filteredTransactions.add(currTransaction);
			}

			if (filteredTransactions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(filteredTransactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/transactions/accounts/to/{accountNumber}")
	@Transactional
	public ResponseEntity<List<Transaction>> getTransactionsTo(@PathVariable("accountNumber") String accountNumber) {

		try {
			List<Transaction> transactions = new ArrayList<Transaction>();
			List<Transaction> filteredTransactions = new ArrayList<Transaction>();

			Account account = accountRepository.findByAccountNumber(accountNumber).orElse(null);
			transactionRepository.findByToAccount(account).forEach(transactions::add);

			// remove the same Invoice from json object returned
			for (int i = 0; i < transactions.size(); i++) {
				Transaction currTransaction = transactions.get(i);
				filteredTransactions.add(currTransaction);
			}

			if (filteredTransactions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(filteredTransactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/transactions/{id}")
	@Transactional
	public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") long id) {
		Optional<Transaction> transactionData = transactionRepository.findById(id);

		if (transactionData.isPresent()) {
			return new ResponseEntity<>(transactionData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/transactions")
	@Transactional
	public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) {

		Invoice invoice = invoiceRepository.findById(transactionDTO.getInvoice()).orElse(null);
		Account from_account = accountRepository.findById(transactionDTO.getFromAccount()).orElse(null);
		Account to_account = accountRepository.findById(transactionDTO.getToAccount()).orElse(null);

		System.out.println(invoice.toString());
		System.out.println(from_account.toString());
		System.out.println(to_account.toString());

		// Check if the supplier and distributor are found
		if (invoice == null || from_account == null || to_account == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		Loan loan = loanRepository.findById(to_account.getAccountId()).orElse(null);
		double transactionAmountAfterRepayment = transactionDTO.getTransactionAmount();
		double interestedAmount = 0.0;
		double repaymentRate = 0.0;

		if (loan != null) {
			repaymentRate = loan.getLoanInterestRate();
			interestedAmount = transactionDTO.getTransactionAmount() * repaymentRate;
			transactionAmountAfterRepayment = transactionDTO.getTransactionAmount()
					- interestedAmount;
		}

		accountRepository.updateAccountBalance(from_account.getAccountId(), -(transactionDTO.getTransactionAmount()));
		accountRepository.updateAccountBalance(0l, interestedAmount);
		accountRepository.updateAccountBalance(to_account.getAccountId(), transactionAmountAfterRepayment);

		if (invoice == null || from_account == null || to_account == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		try {
			Transaction _transaction = transactionRepository
					.save(new Transaction(invoice, transactionDTO.getTransactionDate(),
							transactionDTO.getTransactionAmount(), transactionDTO.getRefNo(), from_account, to_account,
							transactionAmountAfterRepayment, repaymentRate));
			return new ResponseEntity<>(_transaction, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/transactions/{id}")
	public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") long id,
			@RequestBody Transaction transaction) {
		Optional<Transaction> transactionData = transactionRepository.findById(id);

		if (transactionData.isPresent()) {
			Transaction _transaction = transactionData.get();
			_transaction.setInvoice(transaction.getInvoice());
			_transaction.setTransactionDate(transaction.getTransactionDate());
			_transaction.setTransactionAmount(transaction.getTransactionAmount());
			_transaction.setRefNo(transaction.getRefNo());
			_transaction.setFromAccount(transaction.getFromAccount());
			_transaction.setToAccount(transaction.getToAccount());
			_transaction.setTransactionAmountAfterRepayment(transaction.getTransactionAmountAfterRepayment());
			_transaction.setRepaymentRate(transaction.getRepaymentRate());
			return new ResponseEntity<>(transactionRepository.save(_transaction), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/transactions/{id}")
	public ResponseEntity<HttpStatus> deleteTransaction(@PathVariable("id") long id) {
		try {
			transactionRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/transactions")
	public ResponseEntity<HttpStatus> deleteAllCompanies() {
		try {
			transactionRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
