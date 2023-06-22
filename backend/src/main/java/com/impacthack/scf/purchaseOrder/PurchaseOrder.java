package com.impacthack.scf.purchaseOrder;
import com.impacthack.scf.purchaseOrderStatus.PurchaseOrderStatus;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.impacthack.scf.supplier.Supplier;
import com.impacthack.scf.distributor.Distributor;

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
  private Supplier supplier;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "distributor_id")
  private Distributor distributor;

  @JsonIgnoreProperties({"hibernateLazyInitializer"})
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_status")
  private PurchaseOrderStatus purchaseOrderStatus;

  @Column(name = "po_file_name")
  private String poFileName;

  @Column(name = "po_file_type")
  private String poFileType;

  @Lob
  @Column(name = "po_file_data")
  private byte[] poFileData;

  public PurchaseOrder() {

  }

  public PurchaseOrder(double total, Date orderDate, Supplier supplier, Distributor distributor, PurchaseOrderStatus purchaseOrderStatus) {
    this.total = total;
    this.orderDate = orderDate;
    this.supplier = supplier;
    this.distributor = distributor;
    this.purchaseOrderStatus = purchaseOrderStatus;
  }

  public PurchaseOrder(double total, Date orderDate, Supplier supplier, Distributor distributor, PurchaseOrderStatus purchaseOrderStatus, String poFileName, String poFileType, byte[] poFileData) {
    this.total = total;
    this.orderDate = orderDate;
    this.supplier = supplier;
    this.distributor = distributor;
    this.purchaseOrderStatus = purchaseOrderStatus;
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

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public Distributor getDistributor() {
    return distributor;
  }

  public void setDistributor(Distributor distributor) {
    this.distributor = distributor;
  }

  public PurchaseOrderStatus getPurchaseOrderStatus() {
    return purchaseOrderStatus;
  }

  public void setPurchaseOrderStatus(PurchaseOrderStatus purchaseOrderStatus) {
    this.purchaseOrderStatus = purchaseOrderStatus;
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
            ", purchaseOrderStatus=" + purchaseOrderStatus +
            ", poFileName='" + poFileName + '\'' +
            ", poFileType='" + poFileType + '\'' +
            ", poFileData=" + Arrays.toString(poFileData) +
            '}';
  }
}
