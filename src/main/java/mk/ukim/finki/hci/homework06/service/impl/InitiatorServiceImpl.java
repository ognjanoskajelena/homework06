package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Initiator;
import mk.ukim.finki.hci.homework06.model.User;
import mk.ukim.finki.hci.homework06.repository.InitiatorRepository;
import mk.ukim.finki.hci.homework06.service.InitiatorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InitiatorServiceImpl implements InitiatorService {

    private final InitiatorRepository initiatorRepository;

    public InitiatorServiceImpl(InitiatorRepository initiatorRepository) {
        this.initiatorRepository = initiatorRepository;
    }

    @Override
    public Optional<Initiator> findById(Long id) {
        Optional<User> initiator = this.initiatorRepository.findById(id);
        return initiator.map(user -> (Initiator) user);
    }
}
