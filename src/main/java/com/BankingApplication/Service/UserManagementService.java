package com.BankingApplication.Service;

import com.BankingApplication.Entity.User;
import com.BankingApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {
    @Autowired
    UserRepository UsRepo;
    public User saveUser(User us) {
        return UsRepo.save(us);
    }
}
