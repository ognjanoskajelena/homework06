package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Initiative;
import mk.ukim.finki.hci.homework06.repository.InitiativeRepository;
import mk.ukim.finki.hci.homework06.service.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InitiativeServiceImpl implements InitiativeService {

    private final InitiativeRepository initiativeRepository;

    public InitiativeServiceImpl(InitiativeRepository initiativeRepository) {
        this.initiativeRepository = initiativeRepository;
    }

    @Override
    public List<Initiative> findAll() {
        return this.initiativeRepository.findAll();
    }

    @Override
    public Optional<Initiative> findById(Long id) {
        return this.initiativeRepository.findById(id);
    }
}
