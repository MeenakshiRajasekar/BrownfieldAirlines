package com.brownfield.app.service;


import com.brownfield.app.entity.User;
import com.brownfield.app.exception.BadRequestException;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.model.request.LoginRequest;
import com.brownfield.app.model.request.UserRegRequest;
import com.brownfield.app.model.response.LoginResponse;
import com.brownfield.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUserService(UserRegRequest userDto) {
        User user = new User();
        Optional<User> user_db1 =userRepository.findByEmailAddress(userDto.getEmailAddress());
        if(user_db1.isPresent()) {
            throw new BadRequestException("username or email already exists");
        }
        Optional<User> user_db2 =userRepository.findByMobileNumber(userDto.getMobileNumber());
        if(user_db2.isPresent()) {
            throw new BadRequestException("invalid phone");
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmailAddress(userDto.getEmailAddress());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        logger.info("saveUserService method invoked");
        return userRepository.save(user);
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        Optional<User> user=userRepository.findByEmailAddress(loginRequest.getUsername());
        if(user.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(),user.get().getPassword())){
            throw new BadRequestException("invalid username or password");
        }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user.get().getId());
        loginResponse.setFirstName(user.get().getFirstName());
        loginResponse.setLastName(user.get().getLastName());
        loginResponse.setMobileNumber(user.get().getMobileNumber());
        loginResponse.setEmailAddress(user.get().getEmailAddress());
        logger.info("loginUser method invoked");
        return loginResponse;
    }

    @Override
    public User findById(long id) {
        Optional<User> user=userRepository.findById(id);
        if(user.isEmpty()){
            throw new RecordNotFoundException("Record not found for user : " + id);
        }
        logger.info("findById method invoked");
        return user.get();
    }

    @Override
    public User findByUserName(String username) {
        Optional<User> user=userRepository.findByEmailAddress(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        logger.info("findByUserName method invoked");
        return user.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<User> user=userRepository.findByEmailAddress(username);
         if(user.isEmpty()){
             throw new UsernameNotFoundException(username);
         }
         logger.info("loadUserByUsername method invoked");
        return new org.springframework.security.core.userdetails.User(user.get().getEmailAddress(),user.get().getPassword(),new ArrayList<>());    }
}
