package com.BankingApplication.Mapper;

import com.BankingApplication.Dto.AccountDto;
import com.BankingApplication.Dto.UserDto;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.User;

public class UserMapper {
    public static User mapToUser(UserDto usDto)
    {
        return new User(
                usDto.getId(),
                usDto.getUsername(),
                usDto.getEmail(),
                usDto.getPassword(),
                usDto.getRole(),
                usDto.getCreatedAt(),
                usDto.getUpdatedAt()
        );
    }

    public static UserDto mapToUserDto(User us)

    {
        return new UserDto(
                us.getId(),
                us.getUsername(),
                us.getEmail(),
                us.getPassword(),
                us.getRole(),
                us.getCreatedAt(),
                us.getUpdatedAt()
        );
    }
}
