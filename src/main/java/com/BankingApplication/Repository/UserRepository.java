package com.BankingApplication.Repository;

import com.BankingApplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByUsername(String username);
}
