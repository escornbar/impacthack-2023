package com.impacthack.scf.loan;

import jakarta.persistence.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.impacthack.scf.account.Account;
import com.impacthack.scf.loanType.LoanType;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "loans")
public class Loan {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long loanId;

  @Column(name = "loan_interest_rate")
  private double loanInterestRate;

  @Column(name = "loan_amount")
  private double loanAmount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="account_id")
  private Account account;

  @JsonFormat(pattern="dd-MM-yyyy")
  @Column(name = "loan_start_date")
  private Date loanStartDate;

  @JsonIgnoreProperties({"hibernateLazyInitializer"})
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="loan_type")
  private LoanType loanType;

  public Loan(){

  }

  public Loan(long loanId, double loanInterestRate, double loanAmount, Date loanStartDate, LoanType loanType) {
    this.loanId = loanId;
    this.loanInterestRate = loanInterestRate;
    this.loanAmount = loanAmount;
    this.loanStartDate = loanStartDate;
    this.loanType = loanType;
  }

  public Loan(double loanInterestRate, double loanAmount, Account account, Date loanStartDate, LoanType loanType) {
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

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Date getLoanStartDate() {
    return loanStartDate;
  }

  public void setLoanStartDate(Date loanStartDate) {
    this.loanStartDate = loanStartDate;
  }

  public LoanType getLoanType() {
    return loanType;
  }

  public void setLoanType(LoanType loanType) {
    this.loanType = loanType;
  }

  @Override
  public String toString() {
    return "Loan{" +
            "loanId=" + loanId +
            ", loanInterestRate=" + loanInterestRate +
            ", loanAmount=" + loanAmount +
            ", account=" + account +
            ", loanStartDate=" + loanStartDate +
            ", loanType=" + loanType +
            '}';
  }
}
