package com.example.airbnb.service.domain.impl;


import com.example.airbnb.dto.create.CreateAccommodationDTO;
import com.example.airbnb.dto.display.DisplayAccommodationDTO;
import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.model.domains.User;
import com.example.airbnb.model.exceptions.NoAvailableRoomsException;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.HostRepository;
import com.example.airbnb.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public Accommodation create(Accommodation accommodation) throws Exception {
        Host host = this.hostRepository.findById(accommodation.getHost().getId()).orElseThrow(Exception::new);
        Accommodation accommodation1=new Accommodation(accommodation.getName(),accommodation.getCategory(),host,accommodation.getNumRooms());
        return this.accommodationRepository.save(accommodation1);

    }

    @Override
    public Accommodation update(Long id, Accommodation accommodation) throws Exception {
        Host host= this.hostRepository.findById(accommodation.getHost().getId()).orElseThrow(Exception::new);
        Accommodation accommodation1=this.accommodationRepository.findById(id).orElseThrow(Exception::new);

        accommodation.setName(accommodation.getName());
        accommodation.setCategory(accommodation.getCategory());
        accommodation.setHost(host);
        accommodation.setNumRooms(accommodation.getNumRooms());
        return accommodationRepository.save(accommodation1);
    }

    @Override
    public void delete(Long id)  {
        this.accommodationRepository.deleteById(id);
    }

    @Override
    public Accommodation reservation(Long id) throws Exception {
        Accommodation accommodation=this.accommodationRepository.findById(id).orElseThrow(Exception::new);

        if (accommodation.getNumRooms() == 0) {
            throw new NoAvailableRoomsException(id);
        }

        accommodation.setNumRooms(accommodation.getNumRooms() - 1);
        return this.accommodationRepository.save(accommodation);
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public boolean rentById(Long id, User user) {
        return accommodationRepository.findById(id)
                .filter(acc -> acc.getNumRooms() > 0)
                .map(acc -> {
                    acc.setNumRooms(acc.getNumRooms() - 1);
                    accommodationRepository.save(acc);
                    user.getRentedAccommodations().add(acc);
                    return true;
                })
                .orElseThrow(() -> new NoAvailableRoomsException(id));
    }

    @Override
    public boolean returnById(Long id, User user) {
        return accommodationRepository.findById(id)
                .map(acc -> {
                    acc.setNumRooms(acc.getNumRooms() + 1);
                    accommodationRepository.save(acc);
                    user.getRentedAccommodations().removeIf(a -> a.getId().equals(id));
                    return true;
                })
                .orElseThrow(() -> new RuntimeException("Accommodation not found with id: " + id));
    }

    @Override
    public void addToWishlist(Long id, User user) {
        for (Accommodation acc : user.getWishlist()) {
            if (acc.getId().equals(id)) {
                throw new RuntimeException("Accommodation already in wishlist: " + id);
            }
        }

        Accommodation acc = accommodationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accommodation not found: " + id));

        if (acc.getNumRooms() < 1) {
            throw new NoAvailableRoomsException(id);
        }

        user.getWishlist().add(acc);
    }

    @Override
    public void removeFromWishlist(Long id, User user) {
        user.getWishlist().removeIf(acc -> acc.getId().equals(id));
    }

    @Override
    public void rentAllFromWishlist(User user) {
        if (user.getWishlist().isEmpty()) {
            throw new RuntimeException("Wishlist is empty");
        }

        if (!areAccommodationsAvailable(user.getWishlist())) {
            throw new NoAvailableRoomsException(0L);
        }

        for (Accommodation acc : user.getWishlist()) {
            rentById(acc.getId(), user);
        }
    }

    @Override
    public List<Accommodation> findAllFromWishlist(User user) {
        return user.getWishlist();
    }

    public boolean areAccommodationsAvailable(List<Accommodation> accommodations) {
        for (Accommodation acc : accommodations) {
            if (acc.getNumRooms() < 1) {
                return false;
            }
        }
        return true;
    }


}
