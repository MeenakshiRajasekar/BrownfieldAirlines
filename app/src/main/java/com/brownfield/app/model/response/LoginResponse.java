package com.brownfield.app.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String mobileNumber;
}
