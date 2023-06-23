package com.impacthack.scf.transaction;

import jakarta.persistence.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.impacthack.scf.account.Account;
import com.impacthack.scf.invoice.Invoice;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "transactions")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long transactionId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="invoice_id")
  private Invoice invoice;

  @JsonFormat(pattern="dd-MM-yyyy")
  @Column(name = "transaction_date")
  private Date transactionDate;

  @Column(name = "transaction_amount")
  private double transactionAmount;

  @Column(name = "ref_no")
  private String refNo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="from_account")
  private Account fromAccount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="to_account")
  private Account toAccount;

  @Column(name = "transaction_amount_after_repayment")
  private double transactionAmountAfterRepayment;

  @Column(name = "repayment_rate")
  private double repaymentRate;

  public Transaction() {

  }

  public Transaction(Invoice invoice, Date transactionDate, double transactionAmount, String refNo, Account fromAccount, Account toAccount, double transactionAmountAfterRepayment, double repaymentRate) {
    this.invoice = invoice;
    this.transactionDate = transactionDate;
    this.transactionAmount = transactionAmount;
    this.refNo = refNo;
    this.fromAccount = fromAccount;
    this.toAccount = toAccount;
    this.transactionAmountAfterRepayment = transactionAmountAfterRepayment;
    this.repaymentRate = repaymentRate;
  }

  public Transaction(long transactionId, Date transactionDate, double transactionAmount, String refNo, Account fromAccount, Account toAccount, double transactionAmountAfterRepayment, double repaymentRate) {
    this.transactionId = transactionId;
    this.transactionDate = transactionDate;
    this.transactionAmount = transactionAmount;
    this.refNo = refNo;
    this.fromAccount = fromAccount;
    this.toAccount = toAccount;
    this.transactionAmountAfterRepayment = transactionAmountAfterRepayment;
    this.repaymentRate = repaymentRate;
  }

  public long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(long transactionId) {
    this.transactionId = transactionId;
  }

  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

  public double getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(double transactionAmount) {
    this.transactionAmount = transactionAmount;
  }

  public String getRefNo() {
    return refNo;
  }

  public void setRefNo(String refNo) {
    this.refNo = refNo;
  }

  public Account getFromAccount() {
    return fromAccount;
  }

  public void setFromAccount(Account fromAccount) {
    this.fromAccount = fromAccount;
  }

  public Account getToAccount() {
    return toAccount;
  }

  public void setToAccount(Account toAccount) {
    this.toAccount = toAccount;
  }

  public double getTransactionAmountAfterRepayment() {
    return transactionAmountAfterRepayment;
  }

  public void setTransactionAmountAfterRepayment(double transactionAmountAfterRepayment) {
    this.transactionAmountAfterRepayment = transactionAmountAfterRepayment;
  }

  public double getRepaymentRate() {
    return repaymentRate;
  }

  public void setRepaymentRate(double repaymentRate) {
    this.repaymentRate = repaymentRate;
  }

  @Override
  public String toString() {
    return "Transaction{" +
            "transactionId=" + transactionId +
            ", invoice=" + invoice +
            ", transactionDate=" + transactionDate +
            ", transactionAmount=" + transactionAmount +
            ", refNo='" + refNo + '\'' +
            ", fromAccount=" + fromAccount +
            ", toAccount=" + toAccount +
            ", transactionAmountAfterRepayment=" + transactionAmountAfterRepayment +
            ", repaymentRate=" + repaymentRate +
            '}';
  }
}
