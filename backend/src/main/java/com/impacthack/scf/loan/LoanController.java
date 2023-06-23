package com.impacthack.scf.loan;

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
import com.impacthack.scf.loanType.LoanType;
import com.impacthack.scf.loanType.LoanTypeRepository;

@RestController
@RequestMapping("/api")
public class LoanController {

	@Autowired
	LoanRepository loanRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	LoanTypeRepository loanTypeRepository;

	@GetMapping("/loans")
	@Transactional
	public ResponseEntity<List<Loan>> getAllLoans(@RequestParam(required = false) Long accountId) {

		try {
			List<Loan> loans = new ArrayList<Loan>();
			List<Loan> filteredLoans = new ArrayList<Loan>();

			Account account = null;

			if (accountId == null){
				loanRepository.findAll().forEach(filteredLoans::add);
			}
			else{
				account = accountRepository.findById(accountId).orElse(null);
				loanRepository.findByAccount(account).forEach(loans::add);

				// remove the same Invoice from json object returned
				for (int i = 0; i < loans.size(); i++) {
					Loan currLoan = loans.get(i);
					Loan newLoan = new Loan(currLoan.getLoanId(), currLoan.getLoanInterestRate(), currLoan.getLoanAmount(), currLoan.getLoanStartDate(), currLoan.getLoanType());
					filteredLoans.add(newLoan);
				}
			}

			if (filteredLoans.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(filteredLoans, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/loans/{id}")
	@Transactional
	public ResponseEntity<Loan> getLoanById(@PathVariable("id") long id) {
		Optional<Loan> loanData = loanRepository.findById(id);

		if (loanData.isPresent()) {
			return new ResponseEntity<>(loanData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/loans")
	public ResponseEntity<Loan> createLoan(@RequestBody LoanDTO loanDTO) {

			Account account = accountRepository.findById(loanDTO.getAccount()).orElse(null);
			LoanType loanType = loanTypeRepository.findById(loanDTO.getLoanType()).orElse(null);

			// Check if the account are found
			if (account == null || loanType == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

		try {
			Loan _loan = loanRepository.save(new Loan(loanDTO.getLoanInterestRate(), loanDTO.getLoanAmount(), account, loanDTO.getLoanStartDate(), loanType));
			return new ResponseEntity<>(_loan, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/loans/{id}")
	public ResponseEntity<Loan> updateLoan(@PathVariable("id") long id, @RequestBody Loan loan) {
		Optional<Loan> loanData = loanRepository.findById(id);

		if (loanData.isPresent()) {
			Loan _loan = loanData.get();
			_loan.setLoanAmount(loan.getLoanAmount());
			_loan.setLoanStartDate(loan.getLoanStartDate());
			_loan.setLoanInterestRate(loan.getLoanInterestRate());
			return new ResponseEntity<>(loanRepository.save(_loan), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/loans/{id}")
	public ResponseEntity<HttpStatus> deleteLoan(@PathVariable("id") long id) {
		try {
			loanRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/loans")
	public ResponseEntity<HttpStatus> deleteAllCompanies() {
		try {
			loanRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
