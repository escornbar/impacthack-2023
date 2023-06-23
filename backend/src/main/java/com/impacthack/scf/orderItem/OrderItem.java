package com.impacthack.scf.orderItem;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.impacthack.scf.product.Product;
import com.impacthack.scf.purchaseOrder.PurchaseOrder;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "orderItems")
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long orderItemId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="po_id")
  private PurchaseOrder purchaseOrder;

  @JsonIgnoreProperties({"hibernateLazyInitializer"})
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="product_id")
  private Product product;

  @Column(name = "order_item_quantity")
  private int orderItemQuantity;

  @Column(name = "order_item_unit_price")
  private double orderItemUnitPrice;

  public OrderItem() {

  }

  public OrderItem(PurchaseOrder purchaseOrder, Product product, int orderItemQuantity, double orderItemUnitPrice) {
    this.purchaseOrder = purchaseOrder;
    this.product = product;
    this.orderItemQuantity = orderItemQuantity;
    this.orderItemUnitPrice = orderItemUnitPrice;
  }

  public OrderItem(long orderItemId, Product product, int orderItemQuantity, double orderItemUnitPrice) {
    this.orderItemId = orderItemId;
    this.product = product;
    this.orderItemQuantity = orderItemQuantity;
    this.orderItemUnitPrice = orderItemUnitPrice;
  }

  public long getOrderItemId() {
    return orderItemId;
  }

  public void setOrderItemId(long orderItemId) {
    this.orderItemId = orderItemId;
  }

  public PurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getOrderItemQuantity() {
    return orderItemQuantity;
  }

  public void setOrderItemQuantity(int orderItemQuantity) {
    this.orderItemQuantity = orderItemQuantity;
  }

  public double getOrderItemUnitPrice() {
    return orderItemUnitPrice;
  }

  public void setOrderItemUnitPrice(double orderItemUnitPrice) {
    this.orderItemUnitPrice = orderItemUnitPrice;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "OrderItem{" +
            "orderItemId=" + orderItemId +
            ", purchaseOrder=" + purchaseOrder +
            ", product=" + product +
            ", orderItemQuantity=" + orderItemQuantity +
            ", orderItemUnitPrice=" + orderItemUnitPrice +
            '}';
  }
}
