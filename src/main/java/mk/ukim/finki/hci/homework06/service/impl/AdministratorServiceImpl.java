package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Administrator;
import mk.ukim.finki.hci.homework06.model.Initiator;
import mk.ukim.finki.hci.homework06.model.User;
import mk.ukim.finki.hci.homework06.model.exception.AdministratorNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.UsernameAlreadyExistsException;
import mk.ukim.finki.hci.homework06.repository.AdministratorRepository;
import mk.ukim.finki.hci.homework06.service.AdministratorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepository administratorRepository;

    public AdministratorServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Optional<Administrator> findById(Long id) {
        Optional<User> administrator = this.administratorRepository.findById(id);
        return administrator.map(user -> (Administrator) user);
    }

    @Override
    public Optional<Administrator> save(String name, String surname, String username, String password, String email) {
        if(this.administratorRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }
        return Optional.of(this.administratorRepository
                .save(new Administrator(name, surname, username, password, email)));
    }

    @Override
    public Optional<Administrator> update(Long id, String name, String surname, String username, String password,
                                          String email) {
        Optional<Administrator> administrator = this.findById(id);
        if(administrator.isEmpty())
            throw new AdministratorNotFoundException(id);

        Administrator admin = administrator.get();
        if (!password.isEmpty() && !admin.getPassword().equals(password)) {
            admin.setPassword(password);
        }
        admin.setName(name);
        admin.setSurname(surname);
        admin.setUsername(username);
        admin.setEmail(email);

        return Optional.of(this.administratorRepository.save(admin));
    }
}
