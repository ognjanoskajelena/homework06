package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> login(String username, String password);

    Optional<User> register(String name, String surname, String username, String password, String repeatedPassword,
                            String email, String birthDate);
}
