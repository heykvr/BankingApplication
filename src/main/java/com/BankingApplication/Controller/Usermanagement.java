package com.BankingApplication.Controller;

import com.BankingApplication.Entity.User;
import com.BankingApplication.Service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Usermanagement {

    @Autowired
    UserManagementService ums;

    @PostMapping("/add_user")
    public User createUser(@RequestBody User us)
    {

        return ums.saveUser(us) ;
    }
}
