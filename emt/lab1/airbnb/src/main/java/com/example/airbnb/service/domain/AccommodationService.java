package com.example.airbnb.service.domain;

import com.example.airbnb.dto.create.CreateAccommodationDTO;
import com.example.airbnb.dto.display.DisplayAccommodationDTO;
import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.User;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Accommodation create(Accommodation accommodation) throws Exception;
    Accommodation update(Long id, Accommodation accommodation) throws Exception;
    void delete(Long id);

//    Accommodation reservation(Long id) throws Exception;



    void addToTemporarilyList(Long id, String token);//spored user
    void removeFromTemporarilyList(Long id, String token);//spored user
    List<Accommodation> findAllFromTemporarilyList(String token);//spored user
    boolean rentByAccommodationById(Long id, String token);//spored user
    void rentAllFromTemporarilyList(String token);//spored user


    //koa iznajmime smeshchaj
    // go vo dodavame koshnicka
    //mozhe da go ostranime
    //go listame spored userot
    //go iznajmuvame odnosno ja potvrduvame koshnickata
    //da iznejmi direktno edno smestuvanje

    //da se refreshiruva materijalajzd

    void refreshMaterializedView();
}
