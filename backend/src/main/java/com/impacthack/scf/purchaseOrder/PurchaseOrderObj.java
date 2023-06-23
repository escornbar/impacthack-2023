package com.impacthack.scf.purchaseOrder;

import java.util.Date;

import com.impacthack.scf.distributor.Distributor;
import com.impacthack.scf.invoice.InvoiceObj;
import com.impacthack.scf.purchaseOrderStatus.PurchaseOrderStatus;
import com.impacthack.scf.supplier.Supplier;

public class PurchaseOrderObj {

  private long poId;
  private double total;
  private Date orderDate;
  private Supplier supplier;
  private Distributor distributor;
  private PurchaseOrderStatus purchaseOrderStatus;
  private String poFileName;
  private String poFileType;
  private byte[] poFileData;
  private InvoiceObj invoice;

  public PurchaseOrderObj(long poId, double total, Date orderDate, Supplier supplier, Distributor distributor, PurchaseOrderStatus purchaseOrderStatus, String poFileName, String poFileType, byte[] poFileData, InvoiceObj invoice) {
    this.poId = poId;
    this.total = total;
    this.orderDate = orderDate;
    this.supplier = supplier;
    this.distributor = distributor;
    this.purchaseOrderStatus = purchaseOrderStatus;
    this.poFileName = poFileName;
    this.poFileType = poFileType;
    this.poFileData = poFileData;
    this.invoice = invoice;
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

  public InvoiceObj getInvoice() {
    return invoice;
  }

  public void setInvoice(InvoiceObj invoice) {
    this.invoice = invoice;
  }
}
