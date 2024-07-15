package com.BankingApplication.Controller;

import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.User;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class AccountManagement {
    @Autowired
    AccountRepository AcRepo;

    @Autowired
    UserRepository UsRepo;

    @PostMapping("/add_account")
    public Account saveAccount(@RequestBody Account ac)
    {
        System.out.println(ac);
        AcRepo.save(ac);
        return ac;
    }

    @PostMapping("/add_user")
    public User saveUser(@RequestBody User us)
    {
        UsRepo.save(us);
        return us;
    }


}
