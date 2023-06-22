package com.impacthack.scf.invoice;

import com.impacthack.scf.invoiceStatus.InvoiceStatus;
import com.impacthack.scf.invoiceStatus.InvoiceStatusRepository;
import com.impacthack.scf.purchaseOrder.PurchaseOrder;
import com.impacthack.scf.purchaseOrder.PurchaseOrderRepository;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class InvoiceController {

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
    private InvoiceFileStorageService invoiceFileStorageService;

	@Autowired
	InvoiceStatusRepository invoiceStatusRepository;

	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;

	@GetMapping("/invoices")
	@Transactional
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
	@Transactional
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") long id) {
		Optional<Invoice> invoiceData = invoiceRepository.findById(id);

		if (invoiceData.isPresent()) {
			return new ResponseEntity<>(invoiceData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/invoices")
	public ResponseEntity<Invoice> createInvoice(@RequestPart("data") InvoiceDTO invoiceDTO, @RequestPart("file") MultipartFile file) {

			InvoiceStatus invoiceStatus = invoiceStatusRepository.findById(invoiceDTO.getInvoiceStatus()).orElse(null);
			PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(invoiceDTO.getPurchaseOrder()).orElse(null);

			// Check if the supplier and distributor are found
			if (invoiceStatus == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			Invoice invoiceData = new Invoice(invoiceDTO.getTotal(), invoiceDTO.getIssuedDate(), invoiceDTO.getPaymentDeadline(), purchaseOrder, invoiceStatus, invoiceDTO.getDownPayment(), invoiceDTO.getRemainingPayment());

			try {
			Invoice _invoice = invoiceFileStorageService.store(file, invoiceData);

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

			_invoice.setIssuedDate(invoice.getIssuedDate());
			_invoice.setDownPayment(invoice.getDownPayment());
			_invoice.setTotal(invoice.getTotal());
			_invoice.setPaymentDeadline(invoice.getPaymentDeadline());
			_invoice.setRemainingPayment(invoice.getRemainingPayment());
			_invoice.setInvoiceStatus(invoice.getInvoiceStatus());
			_invoice.setPurchaseOrder(invoice.getPurchaseOrder());
			_invoice.setInvoiceFileName(invoice.getInvoiceFileName());
			_invoice.setInvoiceFileType(invoice.getInvoiceFileType());
			_invoice.setInvoiceFileData(invoice.getInvoiceFileData());

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
