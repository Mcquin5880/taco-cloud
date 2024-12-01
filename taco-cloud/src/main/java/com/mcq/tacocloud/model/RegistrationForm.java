package com.mcq.tacocloud.model;

import com.mcq.tacocloud.security.AppUser;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public AppUser toUser(PasswordEncoder passwordEncoder) {
        return new AppUser(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
    }
}
