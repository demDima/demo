package com.metroassesment.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountType type;
    private Double balance;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private AccountDetails details;

    public Long getId() { return id; }
    public AccountType getType() { return type; }
    public Double getBalance() { return balance; }
    public AccountDetails getDetails() { return details; }

    public void setId(Long id) { this.id = id; }
    public void setType(AccountType type) { this.type = type; }
    public void setBalance(Double balance) { this.balance = balance; }
    public void setDetails(AccountDetails details) { 
        this.details = details;
        if (details != null) {
            details.setAccount(this); 
        }
    }
}
