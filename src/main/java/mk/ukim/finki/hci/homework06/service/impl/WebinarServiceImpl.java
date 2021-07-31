package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.User;
import mk.ukim.finki.hci.homework06.model.Webinar;
import mk.ukim.finki.hci.homework06.model.exception.InitiatorNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.WebinarNotFoundException;
import mk.ukim.finki.hci.homework06.repository.WebinarRepository;
import mk.ukim.finki.hci.homework06.service.UserService;
import mk.ukim.finki.hci.homework06.service.WebinarService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class WebinarServiceImpl implements WebinarService {

    private final WebinarRepository webinarRepository;
    private final UserService userService;

    public WebinarServiceImpl(WebinarRepository webinarRepository, UserService userService) {
        this.webinarRepository = webinarRepository;
        this.userService = userService;
    }

    @Override
    public Optional<Webinar> findById(Long id) {
        return this.webinarRepository.findById(id);
    }

    @Override
    public List<Webinar> findAll() {
        return this.webinarRepository.findAll();
    }

    @Override
    public Optional<Webinar> save(String topic, String description, String link, String date, String time, Long initiatorId) {
        Optional<User> initiator = this.userService.findById(initiatorId);
        if(initiator.isPresent()) {
            Webinar webinar = new Webinar(topic, description, link, LocalDate.parse(date), LocalTime.parse(time),
                    initiator.get());
            return Optional.of(this.webinarRepository.save(webinar));
        }
        throw new InitiatorNotFoundException(initiatorId);
    }

    @Override
    public Optional<Webinar> deleteById(Long id) {
        Optional<Webinar> webinar = this.findById(id);
        if(webinar.isPresent()) {
            this.webinarRepository.deleteById(id);
            return webinar;
        }
        throw new WebinarNotFoundException(id);
    }
}
