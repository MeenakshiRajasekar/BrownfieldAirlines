package com.brownfield.app.model.request;

import com.brownfield.app.constant.GenericConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.validation.constraints.*;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegRequest {
    @NotBlank(message = "firstname cannot be empty")
    private String firstName;
    @NotBlank(message = "lastname cannot be empty")
    private String lastName;
    @NotBlank(message = "email cannot be empty")
    @Email(regexp = GenericConstant.EMAILREGEXP,message = "invalid email")
    private String emailAddress;
    @NotBlank(message = "mobile number cannot be empty")
    @Pattern(regexp = GenericConstant.PHONEREGEXP,message = "invalid phone")
    private String mobileNumber;
    @NotEmpty(message = "password cannot be empty")
    @Size(min=8, message="password should have atleast 8 characters")
    private String password;
}
