package com.BankingApplication.Service;

import com.BankingApplication.Dto.UserDto;
import com.BankingApplication.Entity.User;
import com.BankingApplication.Exception.CustomExceptions;
import com.BankingApplication.Mapper.UserMapper;
import com.BankingApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {
    @Autowired
    UserRepository UsRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    public ResponseEntity<UserDto> saveUser(UserDto us) {
        try{

            User a= UserMapper.mapToUser(us);
            String hashPwd=passwordEncoder.encode(a.getPassword());
            a.setPassword(hashPwd);
            User b=UsRepo.save(a);
            UserDto c=UserMapper.mapToUserDto(b);
            return new ResponseEntity<>(c,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            throw new CustomExceptions.BadRequestException("Invalid Data Passed");
        }
    }

    public ResponseEntity<User> getUserById(int id) {
        try {
            Optional<User> lst = UsRepo.findById(id);
            return lst.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
        catch (Exception e)
        {
            throw new CustomExceptions.ResourceNotFoundException("the user with id"+id+"not found");
        }
    }

    public ResponseEntity<User> updateByUserId(int id,User us) {
        Optional<User> lst=UsRepo.findById(id);
        if(lst.isPresent())
        {
            User u=lst.get();
            u.setEmail(us.getEmail());
            return new ResponseEntity<>(u,HttpStatus.OK);
        }
        throw new CustomExceptions.ResourceNotFoundException("The Data you want to delete is not available");
    }
}
