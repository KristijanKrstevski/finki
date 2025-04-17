package com.example.airbnb.dto.display;

import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.User;
import com.example.airbnb.model.enumerations.Role;

import java.util.List;

public record DisplayUserDTO(String username,
                             String name,
                             Role role,
                             List<Long> wishlist,
                             List<Long>rented
                           )
                           {
    public static DisplayUserDTO from(User user){
        return new DisplayUserDTO(
                user.getUsername(),
                user.getName(),
                user.getRole(),
                user.getAccommodationWishlist().stream().map(Accommodation::getId).toList(),
                user.getRentedAccommodation().stream().map(Accommodation::getId).toList()
        );
    }
}
