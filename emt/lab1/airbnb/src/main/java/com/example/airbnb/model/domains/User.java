package com.example.airbnb.model.domains;

import com.example.airbnb.model.enumerations.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {


    @Id
    private String username;

    @JsonIgnore
    private String password;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToMany
    private List<Accommodation> accommodationWishlist;

    @ManyToMany
    private List<Accommodation> rentedAccommodation;


    public void setAccommodationWishlist(List<Accommodation> accommodationWishlist) {
        this.accommodationWishlist = accommodationWishlist;
    }

    public void setRentedAccommodation(List<Accommodation> rentedAccommodation) {
        this.rentedAccommodation = rentedAccommodation;
    }

    public User(String username, String password, String name, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        accommodationWishlist = new ArrayList<>();
        rentedAccommodation = new ArrayList<>();
    }
    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = Role.ROLE_USER;
        accommodationWishlist = new ArrayList<>();
        rentedAccommodation = new ArrayList<>();
    }

    public User(UserDetails userDetails) {
        this.username = userDetails.getUsername();
        this.password = userDetails.getPassword();
        this.role = Role.ROLE_USER;
        accommodationWishlist = new ArrayList<>();
        rentedAccommodation = new ArrayList<>();
    }

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList((GrantedAuthority) role);
    }
    public List<Accommodation> getAccommodationWishlist() {
        return accommodationWishlist;
    }

    public List<Accommodation> getRentedAccommodation() {
        return rentedAccommodation;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
