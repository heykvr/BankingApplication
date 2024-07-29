package com.BankingApplication.Dto;

import com.BankingApplication.Constants.UserTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
   private Integer id;
   private String username;

    private String email;

    private String password;

   private UserTypes role =UserTypes.customer;

    private LocalDateTime createdAt;

   private LocalDateTime updatedAt;
}
