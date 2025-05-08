package com.example.airbnb.service.application;

import com.example.airbnb.dto.create.CreateUserDTO;
import com.example.airbnb.dto.display.DisplayUserDTO;
import com.example.airbnb.dto.login.LoginResponseDTO;
import com.example.airbnb.dto.login.LoginUserDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDTO> register(CreateUserDTO createUserDto);
    //void logout();
    Optional<DisplayUserDTO> findByUsername(String username);


    Optional<LoginResponseDTO> login(LoginUserDTO loginUserDto);
}
