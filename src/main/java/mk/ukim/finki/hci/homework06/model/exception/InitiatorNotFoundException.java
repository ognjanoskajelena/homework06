package mk.ukim.finki.hci.homework06.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InitiatorNotFoundException extends RuntimeException {

    public InitiatorNotFoundException(Long id) {
        super(String.format("Initiator with id %d is not found!", id));
    }
}
