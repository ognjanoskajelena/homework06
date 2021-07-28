package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.User;
import mk.ukim.finki.hci.homework06.model.exception.UserNotFoundException;
import mk.ukim.finki.hci.homework06.repository.UserRepository;
import mk.ukim.finki.hci.homework06.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> save(User user) {
        return Optional.of(this.userRepository.save(user));
    }

    @Override
    public Optional<User> deleteByUsername(String username) {
        Optional<User> user = this.findByUsername(username);
        if(user.isEmpty())
            throw new UserNotFoundException(username);

        this.userRepository.delete(user.get());
        return user;
    }
}
