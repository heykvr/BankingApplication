package com.BankingApplication.Service;

import com.BankingApplication.Dto.TransactionDto;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Transaction;
import com.BankingApplication.Mapper.TransactionMapper;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService implements TransactionServiceInterface {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    @Override
    public TransactionDto depositAmount(TransactionDto transactionDto) {
        Transaction depositTransaction = TransactionMapper.mapToTransaction(transactionDto);
        // deposit calculations
        int accId;
        accId = depositTransaction.getTo_account_id();
        Account getAccountDetails = accountRepository.getReferenceById(accId);

        BigDecimal balance = getAccountDetails.getBalance();


        Transaction savedTransaction = transactionRepository.save(depositTransaction);
        return TransactionMapper.mapToTransactionDto(savedTransaction);
    }

    @Override
    public TransactionDto withDrawAmount(TransactionDto transactionDto) {
        return null;
    }

    @Override
    public TransactionDto transferAmoount(TransactionDto transactionDto) {
        return null;
    }

    @Override
    public List<TransactionDto> getAllTransactionOfAccount(int accountId) {
        return List.of();
    }

    // deposit method(1 acc id,amount)



    //withdrawal method(1 acc id ,amount)


}
