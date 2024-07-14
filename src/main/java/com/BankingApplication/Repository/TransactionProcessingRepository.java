package com.BankingApplication.Repository;

import com.BankingApplication.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionProcessingRepository extends JpaRepository<Transaction,Long> {

}
