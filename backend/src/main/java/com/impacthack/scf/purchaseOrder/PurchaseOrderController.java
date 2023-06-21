package com.impacthack.scf.purchaseOrder;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.impacthack.scf.supplier.Supplier;
import com.impacthack.scf.supplier.SupplierRepository;

import com.impacthack.scf.distributor.Distributor;
import com.impacthack.scf.distributor.DistributorRepository;
import com.impacthack.scf.purchaseOrderStatus.PurchaseOrderStatus;
import com.impacthack.scf.purchaseOrderStatus.PurchaseOrderStatusRepository;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
    private POFileStorageService poFileStorageService;

	  @Autowired
  private SupplierRepository supplierRepository;

  	  @Autowired
  private DistributorRepository distributorRepository;

  	  @Autowired
  private PurchaseOrderStatusRepository purchaseOrderStatusRepository;

	@GetMapping("/purchaseOrders")
	public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
		try {
			List<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();

			purchaseOrderRepository.findAll().forEach(purchaseOrders::add);

			if (purchaseOrders.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(purchaseOrders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/purchaseOrders/{id}")
	public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable("id") long id) {
		Optional<PurchaseOrder> purchaseOrderData = purchaseOrderRepository.findById(id);

		if (purchaseOrderData.isPresent()) {
			return new ResponseEntity<>(purchaseOrderData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/purchaseOrders")
	public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestPart("data") PurchaseOrderDTO purchaseOrderDTO,
                         @RequestPart("file") MultipartFile file) {
			Supplier supplier = supplierRepository.findById(purchaseOrderDTO.getSupplier()).orElse(null);
			Distributor distributor = distributorRepository.findById(purchaseOrderDTO.getDistributor()).orElse(null);

			PurchaseOrderStatus purchaseOrderStatus = purchaseOrderStatusRepository.findById(purchaseOrderDTO.getPurchaseOrderStatus()).orElse(null);


			// Check if the supplier and distributor are found
			if (supplier == null || distributor == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			PurchaseOrder purchaseOrderData = new PurchaseOrder(purchaseOrderDTO.getTotal(), purchaseOrderDTO.getOrderDate(), supplier, distributor, purchaseOrderStatus);

			try {
			PurchaseOrder _purchaseOrder = poFileStorageService.store(file, purchaseOrderData);

			return new ResponseEntity<>(_purchaseOrder, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/purchaseOrders/{id}")
	public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@PathVariable("id") long id, @RequestBody PurchaseOrder purchaseOrder) {
		Optional<PurchaseOrder> purchaseOrderData = purchaseOrderRepository.findById(id);

		if (purchaseOrderData.isPresent()) {
			PurchaseOrder _purchaseOrder = purchaseOrderData.get();

			_purchaseOrder.setOrderDate(purchaseOrder.getOrderDate());
			_purchaseOrder.setTotal(purchaseOrder.getTotal());
			_purchaseOrder.setDistributor(purchaseOrder.getDistributor());
			_purchaseOrder.setSupplier(purchaseOrder.getSupplier());
			_purchaseOrder.setPurchaseOrderStatus(purchaseOrder.getPurchaseOrderStatus());
			_purchaseOrder.setPoFileName(purchaseOrder.getPoFileName());
			_purchaseOrder.setPoFileType(purchaseOrder.getPoFileType());
			_purchaseOrder.setPoFileData(purchaseOrder.getPoFileData());

			return new ResponseEntity<>(purchaseOrderRepository.save(_purchaseOrder), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/purchaseOrders/{id}")
	public ResponseEntity<HttpStatus> deletePurchaseOrder(@PathVariable("id") long id) {
		try {
			purchaseOrderRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/purchaseOrders")
	public ResponseEntity<HttpStatus> deleteAllPurchaseOrders() {
		try {
			purchaseOrderRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
