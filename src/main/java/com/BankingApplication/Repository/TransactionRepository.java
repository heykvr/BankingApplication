package com.BankingApplication.Repository;

import com.BankingApplication.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    @Query("SELECT t FROM Transaction t WHERE t.from_account_id = :accountId OR t.to_account_id = :accountId")
    List<Transaction> findByFromAccountIdOrToAccountId(@Param("accountId") int accountId);
}
