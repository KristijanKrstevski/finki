package com.example.airbnb.web.controller;

import com.example.airbnb.dto.create.CreateUserDTO;
import com.example.airbnb.dto.display.DisplayUserDTO;
import com.example.airbnb.dto.login.LoginUserDTO;
import com.example.airbnb.service.application.UserApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "User API", description = "Endpoints for user authentication and registration")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }
    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDTO> register(@RequestBody CreateUserDTO createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @PostMapping("/login")
    public ResponseEntity<DisplayUserDTO> login(@RequestBody LoginUserDTO loginUserDto, HttpServletRequest request) {
        DisplayUserDTO body = userApplicationService.login(loginUserDto, request).orElseThrow();
        System.out.println(body.role());
        return ResponseEntity.ok(body);
    }

    @Operation(summary = "User logout", description = "Ends the user's session")
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        userApplicationService.logout();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/role")
    public ResponseEntity<?> getRole(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(userDetails);
    }
}
