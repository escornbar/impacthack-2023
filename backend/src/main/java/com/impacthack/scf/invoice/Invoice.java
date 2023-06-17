package com.impacthack.scf.invoice;
import jakarta.persistence.*;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.impacthack.scf.company.Company;
import com.impacthack.scf.enums.OrderStatus;

@Entity
@Table(name = "invoice")
public class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "total")
  private double total;

  @DateTimeFormat(pattern = "dd-MM-yyyy")
  @Column(name = "order_date")
  private Date orderDate;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "company_id")
  private Company supplier;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "company_id")
  private Company distributor;

  @Enumerated(EnumType.STRING)
  @Column(name="orderStatus")
  private OrderStatus orderStatus;

  public Invoice() {

  }

  public Invoice(double total, Date orderDate, Company supplier, Company distributor) {
    this.total = total;
    this.orderDate = orderDate;
    this.supplier = supplier;
    this.distributor = distributor;
    this.orderStatus = OrderStatus.PENDING;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public Company getSupplier() {
    return supplier;
  }

  public void setSupplier(Company supplier) {
    this.supplier = supplier;
  }

  public Company getDistributor() {
    return distributor;
  }

  public void setDistributor(Company distributor) {
    this.distributor = distributor;
  }

  public long getId() {
    return id;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  @Override
  public String toString() {
    return "PurchaseOrder{" +
            "id=" + id +
            ", total=" + total +
            ", orderDate=" + orderDate +
            ", supplier=" + supplier +
            ", distributor=" + distributor +
            ", orderStatus=" + orderStatus +
            '}';
  }
}
