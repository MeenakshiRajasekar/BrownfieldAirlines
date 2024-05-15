package com.brownfield.app.controller;

import com.brownfield.app.entity.User;
import com.brownfield.app.model.request.LoginRequest;
import com.brownfield.app.model.request.UserRegRequest;
import com.brownfield.app.model.response.LoginResponse;
import com.brownfield.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser( @RequestBody @Valid UserRegRequest userDto){
        User response = userService.saveUserService(userDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser( @RequestBody @Valid LoginRequest userDto){
        LoginResponse response = userService.loginUser(userDto);
        return new ResponseEntity(response, HttpStatus.OK);

    }

}
