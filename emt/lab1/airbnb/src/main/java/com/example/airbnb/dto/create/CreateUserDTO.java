package com.example.airbnb.dto.create;

import com.example.airbnb.model.domains.User;
import com.example.airbnb.model.enumerations.Role;

public record CreateUserDTO(String username,
                            String password,
                            String repeatPassword,
                            String name,
                            Role role) {

    public User toUser() {
        return new User(username, password, name, role);
    }
}
