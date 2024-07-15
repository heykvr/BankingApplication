package com.BankingApplication.Mapper;

import com.BankingApplication.Dto.TransactionDto;
import com.BankingApplication.Entity.Transaction;

public class TransactionMapper {

    public static Transaction mapToTransaction(TransactionDto transactionDto) {
        return new Transaction(
                transactionDto.getId(),
                transactionDto.getFrom_account_id(),
                transactionDto.getTo_account_id(),
                transactionDto.getType(),
                transactionDto.getAmount(),
                transactionDto.getBalance_after(),
                transactionDto.getTimestamp()
        );
    }


    public static TransactionDto mapToTransactionDto(Transaction transaction){
        return new TransactionDto(
                transaction.getId(),
                transaction.getFrom_account_id(),
                transaction.getTo_account_id(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getBalance_after(),
                transaction.getTimestamp()
        );
    }
}



