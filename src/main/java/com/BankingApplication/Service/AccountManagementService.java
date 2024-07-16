package com.BankingApplication.Service;

import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.User;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountManagementService {
    @Autowired
    AccountRepository AcRepo;


    public Account saveAccount(Account ac) {
        return AcRepo.save(ac);
    }


    public List<Account> getAccountDetails() {
        List<Account> lst=AcRepo.findAll();
        if(lst.isEmpty())
        {
            System.out.println("there is no details");
            return new ArrayList<>();
        }
        return lst;
    }

    public Account getAccountDetailsById(int id) {
        Optional<Account> lst=AcRepo.findById(id);
        if(lst.isEmpty()) return new Account();
        return lst.get();
    }
}
