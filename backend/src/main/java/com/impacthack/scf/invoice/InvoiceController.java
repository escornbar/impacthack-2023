package com.impacthack.scf.invoice;

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
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class InvoiceController {

	@Autowired
	InvoiceRepository invoiceRepository;

	@GetMapping("/invoices")
	public ResponseEntity<List<Invoice>> getAllInvoices() {
		try {
			List<Invoice> invoices = new ArrayList<Invoice>();

			invoiceRepository.findAll().forEach(invoices::add);

			if (invoices.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(invoices, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/invoices/{id}")
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") long id) {
		Optional<Invoice> companyData = invoiceRepository.findById(id);

		if (companyData.isPresent()) {
			return new ResponseEntity<>(companyData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/invoices")
	public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
		try {
			Invoice _invoice = invoiceRepository.save(new Invoice(invoice.getTotal(), invoice.getOrderDate(), invoice.getSupplier(), invoice.getDistributor()));
			return new ResponseEntity<>(_invoice, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/invoices/{id}")
	public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {
		Optional<Invoice> invoiceData = invoiceRepository.findById(id);

		if (invoiceData.isPresent()) {
			Invoice _invoice = invoiceData.get();

			_invoice.setOrderDate(invoice.getOrderDate());
			_invoice.setTotal(invoice.getTotal());
			_invoice.setDistributor(invoice.getDistributor());
			_invoice.setSupplier(invoice.getSupplier());
			_invoice.setOrderStatus(invoice.getOrderStatus());
			return new ResponseEntity<>(invoiceRepository.save(_invoice), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/invoices/{id}")
	public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable("id") long id) {
		try {
			invoiceRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/invoices")
	public ResponseEntity<HttpStatus> deleteAllInvoices() {
		try {
			invoiceRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
