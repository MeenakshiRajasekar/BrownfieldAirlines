package com.brownfield.app.service;

import com.brownfield.app.entity.User;
import com.brownfield.app.model.request.LoginRequest;
import com.brownfield.app.model.request.UserRegRequest;
import com.brownfield.app.model.response.LoginResponse;

public interface UserService {
    User saveUserService(UserRegRequest userDto);
    LoginResponse loginUser(LoginRequest loginRequest);
    User findById(long id);
    User findByUserName(String username);
}
