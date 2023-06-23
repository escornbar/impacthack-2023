package com.impacthack.scf.account;

import java.util.Date;

public class AccountDTO {

  private long accountId;
  private String accountNumber;
  private String accountName;
  private Date accountDateOpened;
  private double accountBalance;
  private long owner;
  private String accountPurpose;

  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public Date getAccountDateOpened() {
    return accountDateOpened;
  }

  public void setAccountDateOpened(Date accountDateOpened) {
    this.accountDateOpened = accountDateOpened;
  }

  public double getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(double accountBalance) {
    this.accountBalance = accountBalance;
  }

  public long getOwner() {
    return owner;
  }

  public void setOwner(long owner) {
    this.owner = owner;
  }

  public String getAccountPurpose() {
    return accountPurpose;
  }

  public void setAccountPurpose(String accountPurpose) {
    this.accountPurpose = accountPurpose;
  }
}
