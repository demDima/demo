package com.metroassesment.demo.service;


import com.metroassesment.demo.dto.AccountRequest;
import com.metroassesment.demo.dto.AccountSummary;
import com.metroassesment.demo.dto.CustomerResponse;
import com.metroassesment.demo.model.Account;
import com.metroassesment.demo.model.AccountDetails;
import com.metroassesment.demo.repository.AccountRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    
    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public Long createAndReturnId(AccountRequest req) {
        Account account = new Account();

        account.setType(req.getAccountType());
        account.setBalance(0.0);

        AccountDetails details = new AccountDetails();
        details.setCustomerName(req.getCustomerName());
        details.setCustomerMobile(req.getCustomerMobile());
        details.setCustomerEmail(req.getCustomerEmail());
        details.setAddress1(req.getAddress1());
        details.setAddress2(req.getAddress2());

        account.setDetails(details);

        Account saved = repo.save(account);

        return saved.getId(); // 👈 this becomes customerNumber
    }
    public CustomerResponse getCustomer(Long id) {

        Account acc = repo.findById(id).orElse(null);

        if (acc == null) return null;

        CustomerResponse res = new CustomerResponse();

        res.setCustomerNumber(String.valueOf(acc.getId()));

        if (acc.getDetails() != null) {
            res.setCustomerName(acc.getDetails().getCustomerName());
            res.setCustomerMobile(acc.getDetails().getCustomerMobile());
            res.setCustomerEmail(acc.getDetails().getCustomerEmail());
            res.setAddress1(acc.getDetails().getAddress1());
            res.setAddress2(acc.getDetails().getAddress2());
        }

        AccountSummary summary = new AccountSummary();
        summary.setAccountNumber(acc.getId());
        summary.setAccountType(
            acc.getType().equals("S") ? "Savings" : "Checking"
        );
        summary.setAvailableBalance(acc.getBalance());

        res.setSavings(List.of(summary));

        res.setTransactionStatusCode(200);
        res.setTransactionStatusDescription("Customer Account found");

        return res;
    }

}