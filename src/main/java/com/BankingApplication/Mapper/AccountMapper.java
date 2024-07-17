package com.BankingApplication.Mapper;

import com.BankingApplication.Dto.AccountDto;
import com.BankingApplication.Entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto actDto)
    {
        return new Account(
                actDto.getAct_id(),
                actDto.getUser_id(),
                actDto.getAccount_number(),
                actDto.getBalance(),
                actDto.getStatus(),
                actDto.getCreatedAt(),
                actDto.getUpdatedAt()
        );
    }

    public static AccountDto mapToAccountDto(Account ac)

    {
        return new AccountDto(
                ac.getAct_id(),
                ac.getUser_id(),
                ac.getAccount_number(),
                ac.getBalance(),
                ac.getStatus(),
                ac.getCreatedAt(),
                ac.getUpdatedAt()
        );
    }
}

