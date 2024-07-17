package com.BankingApplication.Service;

import com.BankingApplication.Dto.AccountDto;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.User;
import com.BankingApplication.Mapper.AccountMapper;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountManagementService {
    @Autowired
    AccountRepository AcRepo;


    public ResponseEntity<AccountDto> saveAccount(AccountDto actDto) {
        try{
            Account act= AccountMapper.mapToAccount(actDto);
            AccountDto adto=AccountMapper.mapToAccountDto(AcRepo.save(act));
            return new ResponseEntity<>( HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<List<Account>> getAccountDetails() {

        try{
            List<Account> lst=AcRepo.findAll();
            return new ResponseEntity<>(lst, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Account> getAccountDetailsById(int id) {
        Optional<Account> lst=AcRepo.findById(id);
        if(!lst.isEmpty())
        {
            return new ResponseEntity<>(lst.get(), HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Account> deleteById(int id) {

            Optional<Account> lst=AcRepo.findById(id);
            if(!lst.isEmpty())
            {
                AcRepo.deleteById(id);
                return new ResponseEntity<>( HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
