package com.brownfield.app.repository;

import com.brownfield.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmailAddress(String username);
    Optional<User> findByMobileNumber(String phone);

}
