package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Participant;
import mk.ukim.finki.hci.homework06.model.User;
import mk.ukim.finki.hci.homework06.model.exception.InvalidUsernameOrPasswordException;
import mk.ukim.finki.hci.homework06.model.exception.PasswordMismatchException;
import mk.ukim.finki.hci.homework06.model.exception.UsernameAlreadyExistsException;
import mk.ukim.finki.hci.homework06.repository.ParticipantRepository;
import mk.ukim.finki.hci.homework06.repository.UserRepository;
import mk.ukim.finki.hci.homework06.service.AuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;

    public AuthServiceImpl(UserRepository userRepository,
                           ParticipantRepository participantRepository) {
        this.userRepository = userRepository;
        this.participantRepository = participantRepository;
    }

    @Override
    public Optional<User> login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException(username);
        }
        return this.userRepository.findByUsernameAndPassword(username, password).or(Optional::empty);
    }

    @Override
    public Optional<User> register(String name, String surname, String username, String password, String repeatedPassword,
                                   String email, String birthDate) {
        if (!password.equals(repeatedPassword)) {
            throw new PasswordMismatchException();
        }
        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }
        return Optional.of(this.participantRepository
                .save(new Participant(name, surname, username, password, email, LocalDate.parse(birthDate))));
    }
}
