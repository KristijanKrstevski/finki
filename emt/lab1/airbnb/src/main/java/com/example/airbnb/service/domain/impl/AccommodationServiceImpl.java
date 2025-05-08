package com.example.airbnb.service.domain.impl;


import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.model.domains.User;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.HostRepository;
import com.example.airbnb.repository.UserRepository;
import com.example.airbnb.repository.view.AccommodationByHostViewRepository;
import com.example.airbnb.service.domain.AccommodationService;
import com.example.airbnb.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final AccommodationByHostViewRepository accommodationByHostViewRepository;
    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository, UserService userService, UserRepository userRepository, AccommodationByHostViewRepository accommodationByHostViewRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.accommodationByHostViewRepository = accommodationByHostViewRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return this.accommodationRepository.findAll();
    }
    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Accommodation create(Accommodation accommodation) throws Exception {
        Host host = this.hostRepository.findById(accommodation.getHost().getId()).orElseThrow(Exception::new);
        Accommodation accommodation1=new Accommodation(accommodation.getName(),accommodation.getCategory(),host,accommodation.getNumRooms());


        //MATHERILZED prvo prbuvame da go zachuvame pa potoa go refreshirame
        Accommodation savedAccommodation = this.accommodationRepository.save(accommodation1);
//        this.refreshMaterializedView();
        return savedAccommodation;

    }

    @Override
    public Accommodation update(Long id, Accommodation accommodation) throws Exception {
        Host host= this.hostRepository.findById(accommodation.getHost().getId()).orElseThrow(Exception::new);
        Accommodation accommodation1=this.accommodationRepository.findById(id).orElseThrow(Exception::new);

        accommodation1.setName(accommodation.getName());
        accommodation1.setCategory(accommodation.getCategory());
        accommodation1.setHost(host);
        accommodation1.setNumRooms(accommodation.getNumRooms());

        //MATHERILZED prvo prbuvame da go zachuvame pa potoa go refreshirame
        Accommodation updatedAccommodation = this.accommodationRepository.save(accommodation1);
//        this.refreshMaterializedView();
        return updatedAccommodation;
    }

    @Override
    public void delete(Long id)  {
        this.accommodationRepository.deleteById(id);
    }

    @Override
    public void addToTemporarilyList(Long id, String token) {
        User authUser= userService.getAuthenticatedUser(token);
        Accommodation accommodation=accommodationRepository.findById(id).orElseThrow();

        authUser.getAccommodationWishlist().add(accommodation);
        userRepository.save(authUser);

    }

    @Override
    public void removeFromTemporarilyList(Long id, String token) {
        User authUser=userService.getAuthenticatedUser(token);
        Accommodation accommodation=accommodationRepository.findById(id).orElseThrow();
        authUser.getAccommodationWishlist().remove(accommodation);
        userRepository.save(authUser);
    }

    @Override
    public List<Accommodation> findAllFromTemporarilyList( String token) {
        return userService.getAuthenticatedUser(token).getAccommodationWishlist();
    }

    @Override
    public boolean rentByAccommodationById(Long id, String token) {
        User authUser=userService.getAuthenticatedUser(token);
        Accommodation accommodation=accommodationRepository.findById(id).orElseThrow();
        if(accommodation.getNumRooms()==0)
        {
            //throw new NoAvailableRoomsException(id);
            return false;

        }
        authUser.getRentedAccommodation().add(accommodation);
        removeFromTemporarilyList(id,token);
        accommodation.setNumRooms(accommodation.getNumRooms()-1);
        accommodationRepository.save(accommodation);
        userRepository.save(authUser);

        return true;

    }

    @Override
    public void rentAllFromTemporarilyList( String token) {
        User authUser=userService.getAuthenticatedUser(token);
        //da se proverat dali site accommodations imaat prazni sobi
        //ako imaat da se izrentaat
        // od koga kje se izrentaaat da se isprazni listata
        //da se odzeme po edna soba tamu

        List<Accommodation> accommodationsForRent = new ArrayList<>(authUser.getAccommodationWishlist());

        for(Accommodation accommodation : accommodationsForRent)
        {
            if(accommodation.getNumRooms()>0)
            {
                accommodation.setNumRooms(accommodation.getNumRooms() - 1);
                authUser.getRentedAccommodation().add(accommodation);
                removeFromTemporarilyList(accommodation.getId(),token);
                accommodationRepository.save(accommodation);
            }
            else
            {
                continue;
            }
        }
        userRepository.save(authUser);
    }

    @Override
    public void refreshMaterializedView() {
        this.accommodationByHostViewRepository.refreshMaterializedView();
    }


}
