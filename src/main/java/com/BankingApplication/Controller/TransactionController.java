package com.BankingApplication.Controller;
import com.BankingApplication.Dto.TransactionDto;
import com.BankingApplication.Service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("deposit")
    public ResponseEntity<TransactionDto> depositTransaction(@RequestBody TransactionDto transactionDto){
        TransactionDto depositTransaction = transactionService.depositAmount(transactionDto);
        return new ResponseEntity<>(depositTransaction, HttpStatus.CREATED);
    }
    @PostMapping("withdraw")
    public ResponseEntity<TransactionDto> withdrawTransaction(@RequestBody TransactionDto transactionDto){
        TransactionDto withdrawTransaction = transactionService.withDrawAmount(transactionDto);
        return new ResponseEntity<>(withdrawTransaction, HttpStatus.CREATED);
    }
}
