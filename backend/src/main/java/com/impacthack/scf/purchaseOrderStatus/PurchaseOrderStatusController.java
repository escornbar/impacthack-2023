package com.impacthack.scf.purchaseOrderStatus;

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
public class PurchaseOrderStatusController {

	@Autowired
	PurchaseOrderStatusRepository purchaseOrderStatusRepository;

	@GetMapping("/purchaseOrderStatus")
	public ResponseEntity<List<PurchaseOrderStatus>> getAllPurchaseOrderStatus() {
		try {
			List<PurchaseOrderStatus> purchaseOrderStatus = new ArrayList<PurchaseOrderStatus>();

			purchaseOrderStatusRepository.findAll().forEach(purchaseOrderStatus::add);

			if (purchaseOrderStatus.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(purchaseOrderStatus, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/purchaseOrderStatus/{id}")
	public ResponseEntity<PurchaseOrderStatus> getPurchaseOrderStatusById(@PathVariable("id") long id) {
		Optional<PurchaseOrderStatus> purchaseOrderStatusData = purchaseOrderStatusRepository.findById(id);

		if (purchaseOrderStatusData.isPresent()) {
			return new ResponseEntity<>(purchaseOrderStatusData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/purchaseOrderStatus")
	public ResponseEntity<PurchaseOrderStatus> createPurchaseOrderStatus(@RequestBody PurchaseOrderStatus purchaseOrderStatus) {
		try {
			PurchaseOrderStatus _purchaseOrderStatus = purchaseOrderStatusRepository
					.save(new PurchaseOrderStatus(purchaseOrderStatus.getName()));
			return new ResponseEntity<>(_purchaseOrderStatus, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/purchaseOrderStatus/{id}")
	public ResponseEntity<PurchaseOrderStatus> updatePurchaseOrderStatus(@PathVariable("id") long id, @RequestBody PurchaseOrderStatus purchaseOrderStatus) {
		Optional<PurchaseOrderStatus> purchaseOrderStatusData = purchaseOrderStatusRepository.findById(id);

		if (purchaseOrderStatusData.isPresent()) {
			PurchaseOrderStatus _purchaseOrderStatus = purchaseOrderStatusData.get();
			_purchaseOrderStatus.setName(purchaseOrderStatus.getName());
			return new ResponseEntity<>(purchaseOrderStatusRepository.save(_purchaseOrderStatus), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/purchaseOrderStatus/{id}")
	public ResponseEntity<HttpStatus> deletePurchaseOrderStatus(@PathVariable("id") long id) {
		try {
			purchaseOrderStatusRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/purchaseOrderStatus")
	public ResponseEntity<HttpStatus> deleteAllPurchaseOrderStatus() {
		try {
			purchaseOrderStatusRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
