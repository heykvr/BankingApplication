package com.BankingApplication.Config;

import com.BankingApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankingUserDetails implements UserDetailsService {
    @Autowired
    UserRepository usRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName,password;
        List<GrantedAuthority> authorities;
        List<com.BankingApplication.Entity.User> user=usRepo.findByUsername(username);
        if(user.size()==0)
        {
            throw new UsernameNotFoundException("Userdetails not found for the user : "+ username);
        }
        else {
            userName=user.get(0).getUsername();
            password=user.get(0).getPassword();
            authorities=new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.get(0).getRole().toString()));
        }
        return new User(username,password,authorities);
    }
}

