package com.metroassesment.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metroassesment.demo.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
