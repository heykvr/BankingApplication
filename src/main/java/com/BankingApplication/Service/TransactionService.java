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
import java.util.List;
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
        accountRepository.save(accountDetails);
        depositTransaction.setBalance_after(updatedBalance);
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
        if(currentBalance.compareTo(requiredAmountToWithDraw)<0){
            try {
                throw new Exception("Insufficient funds for withdrawal");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        BigDecimal updatedBalance = currentBalance.subtract(requiredAmountToWithDraw);
        getAccountDetails.setBalance(updatedBalance);
        accountRepository.save(getAccountDetails);
        withDrawTransaction.setBalance_after(updatedBalance);
        Transaction savedTransaction = transactionRepository.save(withDrawTransaction);
        return TransactionMapper.mapToTransactionDto(savedTransaction);
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
