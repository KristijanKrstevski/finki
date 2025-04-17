package com.example.airbnb.service.domain;

import com.example.airbnb.model.domains.User;
import com.example.airbnb.model.enumerations.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name,  Role role);

    User login(String username, String password, HttpServletRequest request);

    void logout();

    User getAuthenticatedUser();
    User findByUsername(String username);

}
