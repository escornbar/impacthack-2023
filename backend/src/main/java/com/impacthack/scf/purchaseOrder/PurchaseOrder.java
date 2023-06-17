package com.impacthack.scf.purchaseOrder;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.impacthack.scf.company.Company;
import com.impacthack.scf.enums.OrderStatus;

@Entity
@Table(name = "purchaseOrders")
public class PurchaseOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long poId;

  @Column(name = "total")
  private double total;

  @JsonFormat(pattern="dd-MM-yyyy")
  @Column(name = "order_date")
  private Date orderDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "supplier_id")
  private Company supplier;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "distributor_id")
  private Company distributor;

  @Enumerated(EnumType.STRING)
  @Column(name="order_status")
  private OrderStatus orderStatus;

  @Column(name = "po_file_name")
  private String poFileName;

  @Column(name = "po_file_type")
  private String poFileType;

  @Lob
  @Column(name = "po_file_data")
  private byte[] poFileData;

  public PurchaseOrder() {

  }

    public PurchaseOrder(double total, Date orderDate, Company supplier, Company distributor) {
    this.total = total;
    this.orderDate = orderDate;
    this.supplier = supplier;
    this.distributor = distributor;
    this.orderStatus = OrderStatus.PENDING;
  }

  public PurchaseOrder(double total, Date orderDate, Company supplier, Company distributor, String poFileName, String poFileType, byte[] poFileData) {
    this.total = total;
    this.orderDate = orderDate;
    this.supplier = supplier;
    this.distributor = distributor;
    this.orderStatus = OrderStatus.PENDING;
    this.poFileName = poFileName;
    this.poFileType = poFileType;
    this.poFileData = poFileData;
  }

  public long getPoId() {
    return poId;
  }

  public void setPoId(long poId) {
    this.poId = poId;
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

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  public String getPoFileName() {
    return poFileName;
  }

  public void setPoFileName(String poFileName) {
    this.poFileName = poFileName;
  }

  public String getPoFileType() {
    return poFileType;
  }

  public void setPoFileType(String poFileType) {
    this.poFileType = poFileType;
  }

  public byte[] getPoFileData() {
    return poFileData;
  }

  public void setPoFileData(byte[] poFileData) {
    this.poFileData = poFileData;
  }

  @Override
  public String toString() {
    return "PurchaseOrder{" +
            "poId=" + poId +
            ", total=" + total +
            ", orderDate=" + orderDate +
            ", supplier=" + supplier +
            ", distributor=" + distributor +
            ", orderStatus=" + orderStatus +
            ", poFileName='" + poFileName + '\'' +
            ", poFileType='" + poFileType + '\'' +
            ", poFileData=" + Arrays.toString(poFileData) +
            '}';
  }
}
