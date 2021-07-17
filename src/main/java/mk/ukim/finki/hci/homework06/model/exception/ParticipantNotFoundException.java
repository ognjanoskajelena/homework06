package mk.ukim.finki.hci.homework06.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParticipantNotFoundException extends RuntimeException {

    public ParticipantNotFoundException(Long id) {
        super(String.format("Participant with id %d is not found!", id));
    }
}
