package com.impacthack.scf.payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

import com.impacthack.scf.invoice.Invoice;
import com.impacthack.scf.invoice.InvoiceRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PaymentController {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	InvoiceRepository invoiceRepository;

	@GetMapping("/payments")
	@Transactional
	public ResponseEntity<List<Payment>> getAllPayments(@RequestParam(required = false) Long invoiceId) {

		try {
			List<Payment> payments = new ArrayList<Payment>();
			Invoice invoice = null;

			if (invoiceId == null){
				paymentRepository.findAll().forEach(payments::add);
			}
			else{
				invoice = invoiceRepository.findById(invoiceId).orElse(null);
				paymentRepository.findByInvoice(invoice).forEach(payments::add);
			}

			if (payments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(payments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/payments/{id}")
	@Transactional
	public ResponseEntity<Payment> getPaymentById(@PathVariable("id") long id) {
		Optional<Payment> paymentData = paymentRepository.findById(id);

		if (paymentData.isPresent()) {
			return new ResponseEntity<>(paymentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/payments")
	public ResponseEntity<Payment> createPayment(@RequestBody PaymentDTO paymentDTO) {

			Invoice invoice = invoiceRepository.findById(paymentDTO.getInvoice()).orElse(null);

			// Check if the supplier and distributor are found
			if (invoice == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

		try {
			Payment _payment = paymentRepository.save(new Payment(invoice, paymentDTO.getPaymentDate(), paymentDTO.getPaymentAmount(), paymentDTO.getRefNo()));
			return new ResponseEntity<>(_payment, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/payments/{id}")
	public ResponseEntity<Payment> updatePayment(@PathVariable("id") long id, @RequestBody Payment payment) {
		Optional<Payment> paymentData = paymentRepository.findById(id);

		if (paymentData.isPresent()) {
			Payment _payment = paymentData.get();
			_payment.setInvoice(payment.getInvoice());
			_payment.setPaymentDate(payment.getPaymentDate());
			_payment.setPaymentAmount(payment.getPaymentAmount());
			_payment.setRefNo(payment.getRefNo());
			return new ResponseEntity<>(paymentRepository.save(_payment), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/payments/{id}")
	public ResponseEntity<HttpStatus> deletePayment(@PathVariable("id") long id) {
		try {
			paymentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/payments")
	public ResponseEntity<HttpStatus> deleteAllCompanies() {
		try {
			paymentRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
