package com.example.airbnb.service.application.impl;

import com.example.airbnb.dto.create.CreateUserDTO;
import com.example.airbnb.dto.display.DisplayUserDTO;
import com.example.airbnb.dto.login.LoginUserDTO;
import com.example.airbnb.model.domains.User;
import com.example.airbnb.service.application.UserApplicationService;
import com.example.airbnb.service.domain.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDTO> register(CreateUserDTO createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDTO.from(user));
    }

    @Override
    public Optional<DisplayUserDTO> login(LoginUserDTO loginUserDto, HttpServletRequest request) {
        return Optional.of(DisplayUserDTO.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password(),
                request
        )));
    }

    @Override
    public void logout() {
        userService.logout();
    }

    @Override
    public Optional<DisplayUserDTO> findByUsername(String username) {
        return Optional.of(DisplayUserDTO.from(userService.findByUsername(username)));
    }
}
