package mk.ukim.finki.hci.homework06.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class DiscussionNotFoundException extends RuntimeException {

    public DiscussionNotFoundException(Long id) {
        super(String.format("Discussion with id %d is not found!", id));
    }
}
