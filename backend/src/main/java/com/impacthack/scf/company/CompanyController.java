package com.impacthack.scf.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CompanyController {

	@Autowired
	CompanyRepository companyRepository;

	@GetMapping("/companies")
	public ResponseEntity<List<Company>> getAllCompanies(@RequestParam(required = false) String title) {
		try {
			List<Company> companies = new ArrayList<Company>();

			if (title == null)
				companyRepository.findAll().forEach(companies::add);
			else
				companyRepository.findByNameContaining(title).forEach(companies::add);

			if (companies.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(companies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/companies/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable("id") long id) {
		Optional<Company> companyData = companyRepository.findById(id);

		if (companyData.isPresent()) {
			return new ResponseEntity<>(companyData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/companies")
	public ResponseEntity<Company> createCompany(@RequestBody Company company) {
		try {
			Company _company = companyRepository
					.save(new Company(company.getTitle(), company.getCompanyType(), company.getContactPerson()));
			return new ResponseEntity<>(_company, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/companies/{id}")
	public ResponseEntity<Company> updateCompany(@PathVariable("id") long id, @RequestBody Company company) {
		Optional<Company> companyData = companyRepository.findById(id);

		if (companyData.isPresent()) {
			Company _company = companyData.get();
			_company.setTitle(company.getTitle());
			_company.setContactPerson(company.getContactPerson());
			_company.setCompanyType(company.getCompanyType());
			return new ResponseEntity<>(companyRepository.save(_company), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/companies/{id}")
	public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("id") long id) {
		try {
			companyRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/companies")
	public ResponseEntity<HttpStatus> deleteAllCompanies() {
		try {
			companyRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
