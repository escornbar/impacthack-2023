package com.impacthack.scf.loan;

import java.util.Date;

public class LoanDTO {


  private long loanId;
  private double loanInterestRate;
  private double loanAmount;
  private long account;
  private Date loanStartDate;
  private long loanType;

  public LoanDTO(){

  }

  public LoanDTO(double loanInterestRate, double loanAmount, long account, Date loanStartDate, long loanType) {
    this.loanInterestRate = loanInterestRate;
    this.loanAmount = loanAmount;
    this.account = account;
    this.loanStartDate = loanStartDate;
    this.loanType = loanType;
  }

  public long getLoanId() {
    return loanId;
  }

  public void setLoanId(long loanId) {
    this.loanId = loanId;
  }

  public double getLoanInterestRate() {
    return loanInterestRate;
  }

  public void setLoanInterestRate(double loanInterestRate) {
    this.loanInterestRate = loanInterestRate;
  }

  public double getLoanAmount() {
    return loanAmount;
  }

  public void setLoanAmount(double loanAmount) {
    this.loanAmount = loanAmount;
  }

  public long getAccount() {
    return account;
  }

  public void setAccount(long account) {
    this.account = account;
  }

  public Date getLoanStartDate() {
    return loanStartDate;
  }

  public void setLoanStartDate(Date loanStartDate) {
    this.loanStartDate = loanStartDate;
  }

  public long getLoanType() {
    return loanType;
  }

  public void setLoanType(long loanType) {
    this.loanType = loanType;
  }
}
