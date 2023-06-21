package com.impacthack.scf.orderTrackingStatus;

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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class OrderTrackingStatusController {

	@Autowired
	OrderTrackingStatusRepository orderTrackingStatusRepository;

	@GetMapping("/orderTrackingStatus")
	public ResponseEntity<List<OrderTrackingStatus>> getAllOrderTrackingStatus() {
		try {
			List<OrderTrackingStatus> orderTrackingStatus = new ArrayList<OrderTrackingStatus>();

			orderTrackingStatusRepository.findAll().forEach(orderTrackingStatus::add);

			if (orderTrackingStatus.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(orderTrackingStatus, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/orderTrackingStatus/{id}")
	public ResponseEntity<OrderTrackingStatus> getOrderTrackingStatusById(@PathVariable("id") long id) {
		Optional<OrderTrackingStatus> orderTrackingStatusData = orderTrackingStatusRepository.findById(id);

		if (orderTrackingStatusData.isPresent()) {
			return new ResponseEntity<>(orderTrackingStatusData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/orderTrackingStatus")
	public ResponseEntity<OrderTrackingStatus> createOrderTrackingStatus(@RequestBody OrderTrackingStatus orderTrackingStatus) {
		try {
			OrderTrackingStatus _orderTrackingStatus = orderTrackingStatusRepository
					.save(new OrderTrackingStatus(orderTrackingStatus.getName()));
			return new ResponseEntity<>(_orderTrackingStatus, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/orderTrackingStatus/{id}")
	public ResponseEntity<OrderTrackingStatus> updateOrderTrackingStatus(@PathVariable("id") long id, @RequestBody OrderTrackingStatus orderTrackingStatus) {
		Optional<OrderTrackingStatus> orderTrackingStatusData = orderTrackingStatusRepository.findById(id);

		if (orderTrackingStatusData.isPresent()) {
			OrderTrackingStatus _orderTrackingStatus = orderTrackingStatusData.get();
			_orderTrackingStatus.setName(orderTrackingStatus.getName());
			return new ResponseEntity<>(orderTrackingStatusRepository.save(_orderTrackingStatus), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/orderTrackingStatus/{id}")
	public ResponseEntity<HttpStatus> deleteOrderTrackingStatus(@PathVariable("id") long id) {
		try {
			orderTrackingStatusRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/orderTrackingStatus")
	public ResponseEntity<HttpStatus> deleteAllOrderTrackingStatus() {
		try {
			orderTrackingStatusRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
