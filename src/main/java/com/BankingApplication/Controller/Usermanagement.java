package com.BankingApplication.Controller;

import com.BankingApplication.Dto.UserDto;
import com.BankingApplication.Entity.User;
import com.BankingApplication.Service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Usermanagement {

    @Autowired
    UserManagementService ums;

    @PostMapping("/add_user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto us)
    {
        return ums.saveUser(us) ;
    }

    @GetMapping("/get_user_by_Id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id)
    {
        return ums.getUserById(id);
    }

    @PutMapping("/update_user_by_Id/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable int id,@RequestBody User us)
    {
        return ums.updateByUserId(id,us);
    }
}
