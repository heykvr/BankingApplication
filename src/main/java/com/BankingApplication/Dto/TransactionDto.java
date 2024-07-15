package com.BankingApplication.Dto;


import com.BankingApplication.Constants.TransactionTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private int id;
    private int from_account_id;
    private int to_account_id;
    private TransactionTypes type;
    private BigDecimal amount;
    private BigDecimal balance_after;
    private LocalDateTime timestamp;

}
