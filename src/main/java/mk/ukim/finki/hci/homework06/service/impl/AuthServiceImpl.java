package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Administrator;
import mk.ukim.finki.hci.homework06.model.Initiator;
import mk.ukim.finki.hci.homework06.model.Participant;
import mk.ukim.finki.hci.homework06.model.User;
import mk.ukim.finki.hci.homework06.model.exception.InvalidUsernameOrPasswordException;
import mk.ukim.finki.hci.homework06.model.exception.UsernameAlreadyExistsException;
import mk.ukim.finki.hci.homework06.repository.AdministratorRepository;
import mk.ukim.finki.hci.homework06.repository.InitiatorRepository;
import mk.ukim.finki.hci.homework06.repository.ParticipantRepository;
import mk.ukim.finki.hci.homework06.repository.UserRepository;
import mk.ukim.finki.hci.homework06.service.AuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final InitiatorRepository initiatorRepository;
    private final ParticipantRepository participantRepository;
    private final AdministratorRepository administratorRepository;

    public AuthServiceImpl(UserRepository userRepository,
                           InitiatorRepository initiatorRepository,
                           ParticipantRepository participantRepository,
                           AdministratorRepository administratorRepository) {
        this.userRepository = userRepository;
        this.initiatorRepository = initiatorRepository;
        this.participantRepository = participantRepository;
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Optional<User> login(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException(username);
        }
        return this.userRepository.findByUsernameAndPassword(username, password).or(Optional::empty);
    }

    @Override
    public Optional<User> register(String name, String surname, String username, String password, String email,
                                   String birthDate, String phone) {
        if(this.userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }

        return Optional.of(this.initiatorRepository
                .save(new Initiator(name, surname, username, password, email, LocalDate.parse(birthDate), phone)));
    }

    @Override
    public Optional<User> register(String name, String surname, String username, String password, String email,
                                   String birthDate) {
        if(this.userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }
        return Optional.of(this.participantRepository
                .save(new Participant(name, surname, username, password, email, LocalDate.parse(birthDate))));
    }

    @Override
    public Optional<User> register(String name, String surname, String username, String password, String email) {
        if(this.userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }
        return Optional.of(this.administratorRepository
                .save(new Administrator(name, surname, username, password, email)));
    }
}
