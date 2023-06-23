package com.impacthack.scf.account;

import jakarta.persistence.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.impacthack.scf.company.Company;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "accounts")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long accountId;

  @Column(name = "account_number")
  private String accountNumber;

  @Column(name = "account_name")
  private String accountName;

  @JsonFormat(pattern="dd-MM-yyyy")
  @Column(name = "account_date_opened")
  private Date accountDateOpened;

  @Column(name = "account_balance")
  private double accountBalance;

  @JsonIgnoreProperties({"hibernateLazyInitializer"})
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="owner_id")
  private Company owner;

  @Column(name = "account_purpose")
  private String accountPurpose;

  public Account() {

  }

  public Account(String accountNumber, String accountName, Date accountDateOpened, double accountBalance, Company owner, String accountPurpose) {
    this.accountNumber = accountNumber;
    this.accountName = accountName;
    this.accountDateOpened = accountDateOpened;
    this.accountBalance = accountBalance;
    this.owner = owner;
    this.accountPurpose = accountPurpose;
  }

  public Account(long accountId, String accountNumber, String accountName, Date accountDateOpened, double accountBalance, String accountPurpose) {
    this.accountNumber = accountNumber;
    this.accountId = accountId;
    this.accountName = accountName;
    this.accountDateOpened = accountDateOpened;
    this.accountBalance = accountBalance;
    this.accountPurpose = accountPurpose;
  }

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

  public Company getOwner() {
    return owner;
  }

  public void setOwner(Company owner) {
    this.owner = owner;
  }

  public String getAccountPurpose() {
    return accountPurpose;
  }

  public void setAccountPurpose(String accountPurpose) {
    this.accountPurpose = accountPurpose;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  @Override
  public String toString() {
    return "Account{" +
            "accountId=" + accountId +
            ", accountNumber='" + accountNumber + '\'' +
            ", accountName='" + accountName + '\'' +
            ", accountDateOpened=" + accountDateOpened +
            ", accountBalance=" + accountBalance +
            ", owner=" + owner +
            ", accountPurpose='" + accountPurpose + '\'' +
            '}';
  }
}
