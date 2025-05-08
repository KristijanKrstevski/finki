package com.example.airbnb.service.application.impl;

import com.example.airbnb.dto.create.CreateUserDTO;
import com.example.airbnb.dto.display.DisplayUserDTO;
import com.example.airbnb.dto.login.LoginResponseDTO;
import com.example.airbnb.dto.login.LoginUserDTO;
import com.example.airbnb.model.domains.User;
import com.example.airbnb.security.JwtHelper;
import com.example.airbnb.service.application.UserApplicationService;
import com.example.airbnb.service.domain.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;
    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
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
    public Optional<LoginResponseDTO> login(LoginUserDTO loginUserDto) {
        User user = userService.login(loginUserDto.username(),loginUserDto.password());

        String token = jwtHelper.generateToken(user);
        return Optional.of(new LoginResponseDTO(token));
    }

//    @Override
//    public void logout() {
//        userService.logout();
//    }

    @Override
    public Optional<DisplayUserDTO> findByUsername(String username) {
        return Optional.of(DisplayUserDTO.from(userService.findByUsername(username)));
    }
}
