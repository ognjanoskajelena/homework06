package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Administrator;

import java.util.Optional;

public interface AdministratorService {

    Optional<Administrator> findById(Long id);

    Optional<Administrator> save(String name, String surname, String username, String password, String email);

    Optional<Administrator> update(Long id, String name, String surname, String username, String password,
                                   String email);
}
