package com.impacthack.scf.transaction;

import java.util.Date;

public class TransactionDTO{


  private long transactionId;
  private long invoice;
  private Date transactionDate;
  private double transactionAmount;
  private String refNo;
  private long fromAccount;
  private long toAccount;

  private double transactionAmountAfterRepayment;

  private double repaymentRate;

  public TransactionDTO() {

  }

  public long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(long transactionId) {
    this.transactionId = transactionId;
  }

  public long getInvoice() {
    return invoice;
  }

  public void setInvoice(long invoice) {
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

  public long getFromAccount() {
    return fromAccount;
  }

  public void setFromAccount(long fromAccount) {
    this.fromAccount = fromAccount;
  }

  public long getToAccount() {
    return toAccount;
  }

  public void setToAccount(long toAccount) {
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
}
