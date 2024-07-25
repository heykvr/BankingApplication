package com.BankingApplication.Controller;


import com.BankingApplication.Dto.LoginRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        try {
//            System.out.println("before auth obj");
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//            Authentication newAuthentication = SecurityContextHolder.getContext().getAuthentication();
//            if (null != newAuthentication) {
//
//
//                String secret = "jew9uheiojgepjnoi9";
//                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//                String jwt = Jwts.builder().issuer("Executive Bank").subject("JWT Token")
//                        .claim("username", newAuthentication.getName())
//                        .claim("authorities", newAuthentication.getAuthorities().stream().map(
//                                GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
//                        .issuedAt(new Date())
//                        .expiration(new Date((new Date()).getTime() + 30000000))
//                        .signWith(secretKey).compact();
//                response.setHeader("Authorization", jwt);
//
//            return ResponseEntity.ok("Login successful");
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }

        String jwt = "";
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(),
                loginRequest.getPassword());
        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
        if(null != authenticationResponse && authenticationResponse.isAuthenticated()) {

                String secret = "9mM+eG7dQxPElbz0j7vH7I4e5n+m8QZg8vwXH0S5lY0=";
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                jwt = Jwts.builder().issuer("Executive Bank").subject("JWT Token")
                        .claim("username", authenticationResponse.getName())
                        .claim("authorities", authenticationResponse.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new java.util.Date())
                        .expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
                        .signWith(secretKey).compact();
            }

        return ResponseEntity.status(HttpStatus.OK).header("Authorization",jwt)
                .body( jwt);
    }
}
