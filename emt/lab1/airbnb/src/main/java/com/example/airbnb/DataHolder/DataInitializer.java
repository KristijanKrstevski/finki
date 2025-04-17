package com.example.airbnb.DataHolder;

import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.Country;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.model.domains.User;
import com.example.airbnb.model.enumerations.AccommodationCategory;
import com.example.airbnb.model.enumerations.Role;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.CountryRepository;
import com.example.airbnb.repository.HostRepository;
import com.example.airbnb.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccommodationRepository accommodationRepository;
    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, AccommodationRepository accommodationRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accommodationRepository = accommodationRepository;
    }
    @PostConstruct
    public void initializeData() {
        Country country1 = new Country("Macedonia", "Europe");
        Country country2 = new Country("Brasil", "South America");
        Country country3 = new Country("Japan", "Asia");

        this.countryRepository.save(country1);
        this.countryRepository.save(country2);
        this.countryRepository.save(country3);

        Host host1 = new Host("Ivan", "Pupinoski", country1);
        Host host2 = new Host("Lucas", "Oliveira", country2);
        Host host3 = new Host("Hiroshi", "Tanaka", country3);

        this.hostRepository.save(host1);
        this.hostRepository.save(host2);
        this.hostRepository.save(host3);

        if (this.accommodationRepository.count() == 0) {
            accommodationRepository.save(new Accommodation("Skopje Central Apartment", AccommodationCategory.APARTMENT, host1, 3));
            accommodationRepository.save(new Accommodation("Rio Beach Bungalow", AccommodationCategory.FLAT, host2, 2));
            accommodationRepository.save(new Accommodation("Tokyo Tower View", AccommodationCategory.HOTEL, host3, 10));
        }


        List<User> users = new ArrayList<>();
        if(this.userRepository.count() == 0) {
            users.add(new User("kk", passwordEncoder.encode("kk"), "KrtistijanoROnaldo", Role.ROLE_HOST));
            users.add(new User("user",  passwordEncoder.encode("user"), "User", Role.ROLE_USER));
            userRepository.saveAll(users);
        }
    }
}
