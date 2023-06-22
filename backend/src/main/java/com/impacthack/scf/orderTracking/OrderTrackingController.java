package com.impacthack.scf.orderTracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.impacthack.scf.orderTrackingStatus.OrderTrackingStatus;
import com.impacthack.scf.orderTrackingStatus.OrderTrackingStatusRepository;
import com.impacthack.scf.purchaseOrder.PurchaseOrder;
import com.impacthack.scf.purchaseOrder.PurchaseOrderRepository;

@RestController
@RequestMapping("/api")
public class OrderTrackingController {

	@Autowired
	OrderTrackingRepository orderTrackingRepository;

	@Autowired
	OrderTrackingStatusRepository orderTrackingStatusRepository;

	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;

	@GetMapping("/orderTrackings")
	@Transactional
	public ResponseEntity<List<OrderTracking>> getAllOrderTrackings(@RequestParam(required = false) Long poId) {

		try {
			List<OrderTracking> orderTrackings = new ArrayList<OrderTracking>();
			PurchaseOrder purchaseOrder = null;

			if (poId == null){
				orderTrackingRepository.findAll().forEach(orderTrackings::add);
			}
			else{
				purchaseOrder = purchaseOrderRepository.findById(poId).orElse(null);
				orderTrackingRepository.findByPurchaseOrder(purchaseOrder).forEach(orderTrackings::add);
			}

			if (orderTrackings.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(orderTrackings, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/orderTrackings/{id}")
	@Transactional
	public ResponseEntity<OrderTracking> getOrderTrackingById(@PathVariable("id") long id) {
		Optional<OrderTracking> orderTrackingData = orderTrackingRepository.findById(id);

		if (orderTrackingData.isPresent()) {
			return new ResponseEntity<>(orderTrackingData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/orderTrackings")
	public ResponseEntity<OrderTracking> createOrderTracking(@RequestBody OrderTrackingDTO orderTrackingDTO) {

			PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(orderTrackingDTO.getPurchaseOrder()).orElse(null);
			OrderTrackingStatus orderTrackingStatus = orderTrackingStatusRepository.findById(orderTrackingDTO.getOrderTrackingStatus()).orElse(null);

			// Check if the supplier and distributor are found
			if (purchaseOrder == null || orderTrackingStatus == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

		try {
			OrderTracking _orderTracking = orderTrackingRepository.save(new OrderTracking(orderTrackingDTO.getTrackingNo(), purchaseOrder, orderTrackingDTO.getEstimatedDeliveryDate(), orderTrackingDTO.getActualDeliveryDate(), orderTrackingDTO.getRemarks(), orderTrackingStatus));
			return new ResponseEntity<>(_orderTracking, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/orderTrackings/{id}")
	public ResponseEntity<OrderTracking> updateOrderTracking(@PathVariable("id") long id, @RequestBody OrderTracking orderTracking) {
		Optional<OrderTracking> orderTrackingData = orderTrackingRepository.findById(id);

		if (orderTrackingData.isPresent()) {
			OrderTracking _orderTracking = orderTrackingData.get();
			_orderTracking.setTrackingNo(orderTracking.getTrackingNo());
			_orderTracking.setPurchaseOrder(orderTracking.getPurchaseOrder());
			_orderTracking.setEstimatedDeliveryDate(orderTracking.getEstimatedDeliveryDate());
			_orderTracking.setActualDeliveryDate(orderTracking.getActualDeliveryDate());
			_orderTracking.setRemarks(orderTracking.getRemarks());
			return new ResponseEntity<>(orderTrackingRepository.save(_orderTracking), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/orderTrackings/{id}")
	public ResponseEntity<HttpStatus> deleteOrderTracking(@PathVariable("id") long id) {
		try {
			orderTrackingRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/orderTrackings")
	public ResponseEntity<HttpStatus> deleteAllCompanies() {
		try {
			orderTrackingRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
