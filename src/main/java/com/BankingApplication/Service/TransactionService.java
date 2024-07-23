package com.BankingApplication.Service;
import com.BankingApplication.Dto.TransactionDto;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Transaction;
import com.BankingApplication.Mapper.TransactionMapper;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService implements TransactionServiceInterface {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Transactional
    @Override
    public TransactionDto depositAmount(TransactionDto transactionDto) {
        if (transactionDto == null || transactionDto.getAmount() == null || transactionDto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid transaction data");
        }
        Transaction depositTransaction = TransactionMapper.mapToTransaction(transactionDto);
        int accountId = depositTransaction.getTo_account_id();
        Account accountDetails = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with ID: " + accountId));
        BigDecimal updatedBalance = accountDetails.getBalance().add(transactionDto.getAmount());
        accountDetails.setBalance(updatedBalance);
        depositTransaction.setBalance_after(updatedBalance);
        accountRepository.save(accountDetails);
        Transaction savedTransaction = transactionRepository.save(depositTransaction);
        return TransactionMapper.mapToTransactionDto(savedTransaction);
    }
    @Transactional
    @Override
    public TransactionDto withDrawAmount(TransactionDto transactionDto) {
        if(transactionDto == null || transactionDto.getAmount() == null || transactionDto.getAmount().compareTo(BigDecimal.ZERO) <=0  ){
            throw new IllegalArgumentException("Invalid transaction data");
        }
        Transaction withDrawTransaction = TransactionMapper.mapToTransaction(transactionDto);
        int accountId = withDrawTransaction.getFrom_account_id();
        Account getAccountDetails = accountRepository.findById(accountId)
                .orElseThrow(()-> new EntityNotFoundException("Account not found with Id: "+accountId));
        BigDecimal currentBalance = getAccountDetails.getBalance();
        BigDecimal requiredAmountToWithDraw = transactionDto.getAmount();
        if(currentBalance.compareTo(requiredAmountToWithDraw)<=0){
            try {
                throw new Exception("Insufficient funds for withdrawal");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        BigDecimal updatedBalance = currentBalance.subtract(requiredAmountToWithDraw);
        getAccountDetails.setBalance(updatedBalance);
        accountRepository.save(getAccountDetails);
        Transaction savedTransaction = transactionRepository.save(withDrawTransaction);
        return TransactionMapper.mapToTransactionDto(savedTransaction);
    }

    @Transactional
    @Override
    public TransactionDto transferAmoount(TransactionDto transactionDto) {

        if(transactionDto == null || transactionDto.getAmount() == null || transactionDto.getAmount().compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("invalid transaction data");
        }

        Transaction transferTransaction = TransactionMapper.mapToTransaction(transactionDto);
        int fromAccountId = transferTransaction.getFrom_account_id();
        int toAccountId = transferTransaction.getTo_account_id();
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(()->new EntityNotFoundException("Account not found with Id:" +fromAccountId));
        BigDecimal fromAccountBalance=  fromAccount.getBalance();
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow(()->new EntityNotFoundException("Account not found with Id:" +toAccountId));
        BigDecimal toAccountBalance=  toAccount.getBalance();

        BigDecimal transferringAmount = transactionDto.getAmount();

       if(fromAccountBalance.compareTo(transferringAmount)<=0){
           try {
               throw new Exception("Insufficient funds for transfer in acoount: "+fromAccountId);
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       }

        BigDecimal updateBalanceInFromAccount = fromAccountBalance.subtract(transferringAmount);
        fromAccount.setBalance(updateBalanceInFromAccount);
        accountRepository.save(fromAccount);

        BigDecimal updateBalanceInToAccount = toAccountBalance.add(transferringAmount);
        toAccount.setBalance(updateBalanceInToAccount);
        accountRepository.save(toAccount);
        Transaction savedTransaction = transactionRepository.save(transferTransaction);

        return TransactionMapper.mapToTransactionDto(savedTransaction);
    }

    @Override
    public List<TransactionDto> getAllTransactionOfAccount(int accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with Id: " + accountId));
        List<Transaction> transactions = transactionRepository.findByFromAccountIdOrToAccountId(accountId);
        return transactions.stream()
                .map(TransactionMapper::mapToTransactionDto)
                .collect(Collectors.toList());
    }




}
