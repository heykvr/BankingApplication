package com.BankingApplication.Controller;
import com.BankingApplication.Dto.TransactionDto;
import com.BankingApplication.Service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/deposit")
    public ResponseEntity<TransactionDto> depositTransaction(@RequestBody TransactionDto transactionDto){
        TransactionDto depositTransaction = transactionService.depositAmount(transactionDto);
        return new ResponseEntity<>(depositTransaction, HttpStatus.CREATED);
    }
    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDto> withdrawTransaction(@RequestBody TransactionDto transactionDto){
        TransactionDto withdrawTransaction = transactionService.withDrawAmount(transactionDto);
        return new ResponseEntity<>(withdrawTransaction, HttpStatus.CREATED);
    }
    @PostMapping("transfer")
    public ResponseEntity<TransactionDto> transferTransaction(@RequestBody TransactionDto transactionDto){
        TransactionDto transferTransaction = transactionService.transferAmoount(transactionDto);
        return new ResponseEntity<>(transferTransaction,HttpStatus.CREATED);
    }

    @GetMapping("transactions/{id}")
    public ResponseEntity<List<TransactionDto>> getAllTransactions(@PathVariable("id") int accountId) {
        try {
            List<TransactionDto> transactions = transactionService.getAllTransactionOfAccount(accountId);

            if (transactions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
