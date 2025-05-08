package com.example.airbnb.service.domain.impl;

import com.example.airbnb.model.domains.User;
import com.example.airbnb.model.enumerations.Role;
import com.example.airbnb.repository.UserRepository;
import com.example.airbnb.security.JwtHelper;
import com.example.airbnb.service.domain.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtHelper jwtHelper) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new RuntimeException();

        if (!password.equals(repeatPassword)) throw new RuntimeException();

        if (userRepository.findById(username).isPresent())
            throw new RuntimeException(username);

        User user = new User(username, passwordEncoder.encode(password), name, role);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException();
        }

        User user = findByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException();

        return user;
    }
//    public void setAuthentication(UserDetails userDetails, HttpServletRequest request) {
//        UsernamePasswordAuthenticationToken authToken =
//                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//    }
    @Override
    public void logout() {
        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            SecurityContextHolder.clearContext();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public User getAuthenticatedUser(String token) {
        String username = jwtHelper.extractUsername(token);
        return findByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findById(username).orElseThrow();
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).orElseThrow();
    }
}
