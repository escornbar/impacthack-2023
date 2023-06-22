package com.impacthack.scf.orderItem;

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

import com.impacthack.scf.product.Product;
import com.impacthack.scf.product.ProductRepository;
import com.impacthack.scf.purchaseOrder.PurchaseOrder;
import com.impacthack.scf.purchaseOrder.PurchaseOrderRepository;

@RestController
@RequestMapping("/api")
public class OrderItemController {

	@Autowired
	OrderItemRepository orderItemRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;

	@GetMapping("/orderItems")
	@Transactional
	public ResponseEntity<List<OrderItem>> getAllOrderItems(@RequestParam(required = false) Long poId) {

		try {
			List<OrderItem> orderItems = new ArrayList<OrderItem>();
			PurchaseOrder purchaseOrder = null;

			if (poId == null){
				orderItemRepository.findAll().forEach(orderItems::add);
			}
			else{
				purchaseOrder = purchaseOrderRepository.findById(poId).orElse(null);
				orderItemRepository.findByPurchaseOrder(purchaseOrder).forEach(orderItems::add);
			}

			if (orderItems.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(orderItems, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/orderItems/{id}")
	@Transactional
	public ResponseEntity<OrderItem> getOrderItemById(@PathVariable("id") long id) {
		Optional<OrderItem> orderItemData = orderItemRepository.findById(id);

		if (orderItemData.isPresent()) {
			return new ResponseEntity<>(orderItemData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/orderItems")
	public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {

			PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(orderItemDTO.getPurchaseOrder()).orElse(null);
			Product product = productRepository.findById(orderItemDTO.getProduct()).orElse(null);

			// Check if the purchaseOrder and product are found
			if (purchaseOrder == null || product == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

		try {
			OrderItem _orderItem = orderItemRepository.save(new OrderItem(purchaseOrder, product, orderItemDTO.getOrderItemQuantity(), orderItemDTO.getOrderItemUnitPrice()));
			return new ResponseEntity<>(_orderItem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/orderItems/{id}")
	public ResponseEntity<OrderItem> updateOrderItem(@PathVariable("id") long id, @RequestBody OrderItem orderItem) {
		Optional<OrderItem> orderItemData = orderItemRepository.findById(id);

		if (orderItemData.isPresent()) {
			OrderItem _orderItem = orderItemData.get();
			_orderItem.setProduct(orderItem.getProduct());
			_orderItem.setPurchaseOrder(orderItem.getPurchaseOrder());
			_orderItem.setOrderItemUnitPrice(orderItem.getOrderItemUnitPrice());
			_orderItem.setOrderItemQuantity(orderItem.getOrderItemQuantity());
			return new ResponseEntity<>(orderItemRepository.save(_orderItem), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/orderItems/{id}")
	public ResponseEntity<HttpStatus> deleteOrderItem(@PathVariable("id") long id) {
		try {
			orderItemRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/orderItems")
	public ResponseEntity<HttpStatus> deleteAllCompanies() {
		try {
			orderItemRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
