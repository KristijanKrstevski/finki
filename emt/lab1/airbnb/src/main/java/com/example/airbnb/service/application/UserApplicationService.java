package com.example.airbnb.service.application;

import com.example.airbnb.dto.create.CreateUserDTO;
import com.example.airbnb.dto.display.DisplayUserDTO;
import com.example.airbnb.dto.login.LoginUserDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDTO> register(CreateUserDTO createUserDto);

    Optional<DisplayUserDTO> login(LoginUserDTO loginUserDto, HttpServletRequest request);

    void logout();
    Optional<DisplayUserDTO> findByUsername(String username);
}
