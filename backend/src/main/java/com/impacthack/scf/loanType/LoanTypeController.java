package com.impacthack.scf.loanType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoanTypeController {

	@Autowired
	LoanTypeRepository loanTypeRepository;

	@GetMapping("/loanType")
	public ResponseEntity<List<LoanType>> getAllLoanType() {
		try {
			List<LoanType> loanType = new ArrayList<LoanType>();

			loanTypeRepository.findAll().forEach(loanType::add);

			if (loanType.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(loanType, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/loanType/{id}")
	public ResponseEntity<LoanType> getLoanTypeById(@PathVariable("id") long id) {
		Optional<LoanType> loanTypeData = loanTypeRepository.findById(id);

		if (loanTypeData.isPresent()) {
			return new ResponseEntity<>(loanTypeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/loanType")
	public ResponseEntity<LoanType> createLoanType(@RequestBody LoanType loanType) {
		try {
			LoanType _loanType = loanTypeRepository
					.save(new LoanType(loanType.getName()));
			return new ResponseEntity<>(_loanType, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/loanType/{id}")
	public ResponseEntity<LoanType> updateLoanType(@PathVariable("id") long id, @RequestBody LoanType loanType) {
		Optional<LoanType> loanTypeData = loanTypeRepository.findById(id);

		if (loanTypeData.isPresent()) {
			LoanType _loanType = loanTypeData.get();
			_loanType.setName(loanType.getName());
			return new ResponseEntity<>(loanTypeRepository.save(_loanType), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/loanType/{id}")
	public ResponseEntity<HttpStatus> deleteLoanType(@PathVariable("id") long id) {
		try {
			loanTypeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/loanType")
	public ResponseEntity<HttpStatus> deleteAllLoanType() {
		try {
			loanTypeRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
