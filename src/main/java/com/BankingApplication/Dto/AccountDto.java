package com.BankingApplication.Dto;

import com.BankingApplication.Constants.AccountTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Integer act_id;

    private Integer user_id;

    private String account_number;

    private BigDecimal balance = BigDecimal.ZERO;

    private AccountTypes status = AccountTypes.ACTIVE;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
