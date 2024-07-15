package com.BankingApplication.Entity;


import com.BankingApplication.Constants.TransactionTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Transactions")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int from_account_id;

    private int to_account_id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('deposit','withdrawal','transfer')")
    private TransactionTypes type;

    @Column(precision = 10,scale = 2,columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private BigDecimal amount;

    @Column(precision = 10,scale = 2,columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private BigDecimal balance_after;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime timestamp;



    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }




}
