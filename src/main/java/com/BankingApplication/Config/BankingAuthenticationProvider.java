package com.BankingApplication.Config;

import com.BankingApplication.Entity.User;
import com.BankingApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class BankingAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository usRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();
        List<User> user=usRepo.findByUsername(username);
        if(user.size()>0)
        {
            if(passwordEncoder.matches(password,user.get(0).getPassword()))
            {
                List<GrantedAuthority> authorities=new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.get(0).getRole().toString()));
                return new UsernamePasswordAuthenticationToken(username,password,authorities);

            }
            else {
                throw new BadCredentialsException("Invalid Password!!");
            }
        }
        else
        {
            throw new BadCredentialsException("No User Registered with the details");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
