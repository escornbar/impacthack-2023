package com.impacthack.scf.invoice;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.impacthack.scf.company.Company;
import com.impacthack.scf.purchaseOrder.PurchaseOrder;
import com.impacthack.scf.enums.OrderStatus;

@Entity
@Table(name = "invoices")
public class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long invoiceId;

  @Column(name = "total")
  private double total;

  @JsonFormat(pattern="dd-MM-yyyy")
  @Column(name = "issued_date")
  private Date issuedDate;

    @JsonFormat(pattern="dd-MM-yyyy")
  @Column(name = "payment_deadline")
  private Date paymentDeadline;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "supplier_id")
  private Company supplier;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "distributor_id")
  private Company distributor;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "po_id")
  private PurchaseOrder purchaseOrder;

  @Enumerated(EnumType.STRING)
  @Column(name="order_status")
  private OrderStatus orderStatus;

  @Column(name = "invoice_file_name")
  private String invoiceFileName;

  @Column(name = "invoice_file_type")
  private String invoiceFileType;

  @Column(name = "down_payment")
  private double downPayment;

  @Column(name = "remaining_payment")
  private double remainingPayment;

  @Lob
  @Column(name = "invoice_file_data")
  private byte[] invoiceFileData;

  public Invoice() {

  }

    public Invoice(double total, Date issuedDate, Company supplier, Company distributor, PurchaseOrder purchaseOrder, double downPayment, double remainingPayment, Date paymentDeadline) {
    this.total = total;
    this.issuedDate = issuedDate;
    this.supplier = supplier;
    this.distributor = distributor;
    this.purchaseOrder = purchaseOrder;
    this.downPayment = downPayment;
    this.remainingPayment = remainingPayment;
    this.paymentDeadline = paymentDeadline;
  }

  public Invoice(double total, Date issuedDate, Company supplier, Company distributor, PurchaseOrder purchaseOrder, String invoiceFileName, String invoiceFileType, byte[] invoiceFileData, double downPayment, double remainingPayment, Date paymentDeadline) {
    this.total = total;
    this.issuedDate = issuedDate;
    this.supplier = supplier;
    this.distributor = distributor;
    this.purchaseOrder = purchaseOrder;
    this.invoiceFileName = invoiceFileName;
    this.invoiceFileType = invoiceFileType;
    this.downPayment = downPayment;
    this.remainingPayment = remainingPayment;
    this.invoiceFileData = invoiceFileData;
    this.paymentDeadline = paymentDeadline;
  }

  public long getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(long invoiceId) {
    this.invoiceId = invoiceId;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public Date getIssuedDate() {
    return issuedDate;
  }

  public void setIssuedDate(Date issuedDate) {
    this.issuedDate = issuedDate;
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

  public String getInvoiceFileName() {
    return invoiceFileName;
  }

  public void setInvoiceFileName(String invoiceFileName) {
    this.invoiceFileName = invoiceFileName;
  }

  public String getInvoiceFileType() {
    return invoiceFileType;
  }

  public void setInvoiceFileType(String invoiceFileType) {
    this.invoiceFileType = invoiceFileType;
  }

  public byte[] getInvoiceFileData() {
    return invoiceFileData;
  }

  public void setInvoiceFileData(byte[] invoiceFileData) {
    this.invoiceFileData = invoiceFileData;
  }

  public double getDownPayment() {
    return downPayment;
  }

  public void setDownPayment(double downPayment) {
    this.downPayment = downPayment;
  }

  public double getRemainingPayment() {
    return remainingPayment;
  }

  public void setRemainingPayment(double remainingPayment) {
    this.remainingPayment = remainingPayment;
  }

  public PurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Date getPaymentDeadline() {
    return paymentDeadline;
  }

  public void setPaymentDeadline(Date paymentDeadline) {
    this.paymentDeadline = paymentDeadline;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "Invoice{" +
            "invoiceId=" + invoiceId +
            ", total=" + total +
            ", issuedDate=" + issuedDate +
            ", paymentDeadline=" + paymentDeadline +
            ", supplier=" + supplier +
            ", distributor=" + distributor +
            ", purchaseOrder=" + purchaseOrder +
            ", orderStatus=" + orderStatus +
            ", invoiceFileName='" + invoiceFileName + '\'' +
            ", invoiceFileType='" + invoiceFileType + '\'' +
            ", downPayment=" + downPayment +
            ", remainingPayment=" + remainingPayment +
            ", invoiceFileData=" + java.util.Arrays.toString(invoiceFileData) +
            '}';
  }
}
