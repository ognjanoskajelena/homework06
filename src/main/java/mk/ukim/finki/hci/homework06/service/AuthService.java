package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> login(String username, String password);

    // Register method for Initiator - attributes parameters
    Optional<User> register(String name, String surname, String username, String password, String email,
                            String birthDate, String phone);

    // Register method for Participant - attributes parameters
    Optional<User> register(String name, String surname, String username, String password, String email,
                            String birthDate);

    // Register method for Administrator - attributes parameters
    Optional<User> register(String name, String surname, String username, String password, String email);
}
