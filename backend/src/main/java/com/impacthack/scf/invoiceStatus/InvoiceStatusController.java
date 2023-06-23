package com.impacthack.scf.invoiceStatus;

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
public class InvoiceStatusController {

	@Autowired
	InvoiceStatusRepository invoiceStatusRepository;

	@GetMapping("/invoiceStatus")
	public ResponseEntity<List<InvoiceStatus>> getAllInvoiceStatus() {
		try {
			List<InvoiceStatus> invoiceStatus = new ArrayList<InvoiceStatus>();

			invoiceStatusRepository.findAll().forEach(invoiceStatus::add);

			if (invoiceStatus.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(invoiceStatus, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/invoiceStatus/{id}")
	public ResponseEntity<InvoiceStatus> getInvoiceStatusById(@PathVariable("id") long id) {
		Optional<InvoiceStatus> invoiceStatusData = invoiceStatusRepository.findById(id);

		if (invoiceStatusData.isPresent()) {
			return new ResponseEntity<>(invoiceStatusData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/invoiceStatus")
	public ResponseEntity<InvoiceStatus> createInvoiceStatus(@RequestBody InvoiceStatus invoiceStatus) {
		try {
			InvoiceStatus _invoiceStatus = invoiceStatusRepository
					.save(new InvoiceStatus(invoiceStatus.getName()));
			return new ResponseEntity<>(_invoiceStatus, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/invoiceStatus/{id}")
	public ResponseEntity<InvoiceStatus> updateInvoiceStatus(@PathVariable("id") long id, @RequestBody InvoiceStatus invoiceStatus) {
		Optional<InvoiceStatus> invoiceStatusData = invoiceStatusRepository.findById(id);

		if (invoiceStatusData.isPresent()) {
			InvoiceStatus _invoiceStatus = invoiceStatusData.get();
			_invoiceStatus.setName(invoiceStatus.getName());
			return new ResponseEntity<>(invoiceStatusRepository.save(_invoiceStatus), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/invoiceStatus/{id}")
	public ResponseEntity<HttpStatus> deleteInvoiceStatus(@PathVariable("id") long id) {
		try {
			invoiceStatusRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/invoiceStatus")
	public ResponseEntity<HttpStatus> deleteAllInvoiceStatus() {
		try {
			invoiceStatusRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
