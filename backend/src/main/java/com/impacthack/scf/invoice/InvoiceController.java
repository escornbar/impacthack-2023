package com.impacthack.scf.invoice;

import com.impacthack.scf.company.Company;
import com.impacthack.scf.company.CompanyRepository;
import com.impacthack.scf.purchaseOrder.PurchaseOrder;
import com.impacthack.scf.purchaseOrder.PurchaseOrderRepository;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class InvoiceController {

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
    private InvoiceFileStorageService poFileStorageService;

	  @Autowired
  private CompanyRepository companyRepository;
  private PurchaseOrderRepository purchaseOrderRepository;

	@GetMapping("/invoices")
	public ResponseEntity<List<Invoice>> getAllInvoices() {

		return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
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
		Optional<Invoice> invoiceData = invoiceRepository.findById(id);

		if (invoiceData.isPresent()) {
			return new ResponseEntity<>(invoiceData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/invoices")
	public ResponseEntity<Invoice> createInvoice(@RequestPart("data") InvoiceDTO invoiceDTO,
                         @RequestPart("file") MultipartFile file) {

			return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
			Company supplier = companyRepository.findById(invoiceDTO.getSupplier()).orElse(null);
			Company distributor = companyRepository.findById(invoiceDTO.getDistributor()).orElse(null);
			PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(invoiceDTO.getPurchaseOrder()).orElse(null);

			// Check if the supplier and distributor are found
			if (supplier == null || distributor == null || purchaseOrder == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			Invoice invoiceData = new Invoice(invoiceDTO.getTotal(), invoiceDTO.getIssuedDate(), supplier, distributor, purchaseOrder, invoiceDTO.getDownPayment(), invoiceDTO.getRemainingPayment(), invoiceDTO.getPaymentDeadine());

			try {
			Invoice _invoice = poFileStorageService.store(file, invoiceData);

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
			_invoice.setTotal(invoice.getTotal());
			_invoice.setDistributor(invoice.getDistributor());
			_invoice.setSupplier(invoice.getSupplier());
			_invoice.setDownPayment(invoice.getDownPayment());
			_invoice.setRemainingPayment(invoice.getRemainingPayment());
			_invoice.setOrderStatus(invoice.getOrderStatus());
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
