package com.BankingApplication.Repository;

import com.BankingApplication.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.math.BigDecimal;

public interface AccountRepository extends JpaRepository<Account,Integer> {

}
