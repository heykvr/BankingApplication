package com.BankingApplication.Controller;

import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.User;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.UserRepository;
import com.BankingApplication.Service.AccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class AccountManagement {
    @Autowired
    AccountManagementService ams;

    @PostMapping("/add_account")
    public Account createAccount(@RequestBody Account ac)
    {
        return ams.saveAccount(ac);
    }

    @GetMapping("/get_account_details")
    public List<Account> getAccountDetails()
    {
        return ams.getAccountDetails();
    }
    @GetMapping("/get_account_details_by_Id/{id}")
    public Account getAccountDetailsById(@PathVariable int id)
    {
        return ams.getAccountDetailsById(id);
    }



}
