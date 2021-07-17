package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.User;
import mk.ukim.finki.hci.homework06.model.enums.Role;

import java.time.LocalDate;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> deleteByUsername(String username);
}
