package com.impacthack.scf.account;

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

import com.impacthack.scf.company.Company;
import com.impacthack.scf.distributor.DistributorRepository;
import com.impacthack.scf.supplier.SupplierRepository;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	DistributorRepository distributorRepository;

	@Autowired
	SupplierRepository supplierRepository;

	@GetMapping("/accounts")
	@Transactional
	public ResponseEntity<List<Account>> getAllAccounts(@RequestParam(required = false) Long ownerId) {

		try {
			List<Account> accounts = new ArrayList<Account>();
			List<Account> filteredAccounts = new ArrayList<Account>();
			Company company = null;

			if (ownerId == null){
				accountRepository.findAll().forEach(filteredAccounts::add);
			}
			else{
				company = supplierRepository.findById(ownerId).orElse(null);
				if (company == null){
					company = distributorRepository.findById(ownerId).orElse(null);
				}
				accountRepository.findByOwner(company).forEach(accounts::add);

				// remove the same Purchase Order from json object returned
				for (int i = 0; i < accounts.size(); i++) {
					Account currAccount = accounts.get(i);
					Account newAccount = new Account(currAccount.getAccountId(), currAccount.getAccountNumber(), currAccount.getAccountName(), currAccount.getAccountDateOpened(), currAccount.getAccountBalance(), currAccount.getAccountPurpose());
					filteredAccounts.add(newAccount);
				}
			}

			if (filteredAccounts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(filteredAccounts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/accounts/{id}")
	@Transactional
	public ResponseEntity<Account> getAccountById(@PathVariable("id") long id) {
		Optional<Account> accountData = accountRepository.findById(id);

		if (accountData.isPresent()) {
			return new ResponseEntity<>(accountData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/accounts")
	public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) {
			Company company = null;
			
			company = supplierRepository.findById(accountDTO.getOwner()).orElse(null);
			if (company == null){
					company = distributorRepository.findById(accountDTO.getOwner()).orElse(null);
			}
			// Check if the supplier and distributor are found
			if (company == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

		try {
			Account _account = accountRepository.save(new Account(accountDTO.getAccountNumber(), accountDTO.getAccountName(), accountDTO.getAccountDateOpened(), accountDTO.getAccountBalance(), company, accountDTO.getAccountPurpose()));
			return new ResponseEntity<>(_account, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/accounts/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody Account account) {
		Optional<Account> accountData = accountRepository.findById(id);

		if (accountData.isPresent()) {
			Account _account = accountData.get();
			
			_account.setAccountName(account.getAccountName());
			_account.setAccountDateOpened(account.getAccountDateOpened());
			_account.setAccountBalance(account.getAccountBalance());
			_account.setAccountNumber(account.getAccountNumber());
			_account.setOwner(account.getOwner());
			_account.setAccountPurpose(account.getAccountPurpose());
			return new ResponseEntity<>(accountRepository.save(_account), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") long id) {
		try {
			accountRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/accounts")
	public ResponseEntity<HttpStatus> deleteAllCompanies() {
		try {
			accountRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
