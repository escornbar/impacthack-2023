package com.impacthack.scf.distributor;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DistributorController {

	@Autowired
	DistributorRepository distributorRepository;

	@GetMapping("/distributors")
	public ResponseEntity<List<Distributor>> getAllDistributors(@RequestParam(required = false) String name) {
		try {
			List<Distributor> distributors = new ArrayList<Distributor>();

			if (name == null)
				distributorRepository.findAll().forEach(distributors::add);
			else
				distributorRepository.findByNameContaining(name).forEach(distributors::add);

			if (distributors.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(distributors, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/distributors/{id}")
	public ResponseEntity<Distributor> getDistributorById(@PathVariable("id") long id) {
		Optional<Distributor> distributorData = distributorRepository.findById(id);

		if (distributorData.isPresent()) {
			return new ResponseEntity<>(distributorData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/distributors")
	public ResponseEntity<Distributor> createDistributor(@RequestBody Distributor distributor) {
		try {
			Distributor _distributor = distributorRepository
					.save(new Distributor(distributor.getName(), distributor.getAddress(), distributor.getContactName(), distributor.getContactNo()));
			return new ResponseEntity<>(_distributor, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/distributors/{id}")
	public ResponseEntity<Distributor> updateDistributor(@PathVariable("id") long id, @RequestBody Distributor distributor) {
		Optional<Distributor> distributorData = distributorRepository.findById(id);

		if (distributorData.isPresent()) {
			Distributor _distributor = distributorData.get();
			_distributor.setName(distributor.getName());
			_distributor.setAddress(distributor.getAddress());
			_distributor.setContactName(distributor.getContactName());
						_distributor.setContactNo(distributor.getContactNo());

			return new ResponseEntity<>(distributorRepository.save(_distributor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/distributors/{id}")
	public ResponseEntity<HttpStatus> deleteDistributor(@PathVariable("id") long id) {
		try {
			distributorRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/distributors")
	public ResponseEntity<HttpStatus> deleteAllDistributors() {
		try {
			distributorRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
