package com.BankingApplication.Service;

import com.BankingApplication.Dto.TransactionDto;

import java.util.List;

public interface TransactionServiceInterface {
    TransactionDto depositAmount(TransactionDto transactionDto);

    TransactionDto withDrawAmount(TransactionDto transactionDto);

    TransactionDto transferAmoount(TransactionDto transactionDto);

    List<TransactionDto> getAllTransactionOfAccount(int accountId);

}
