package nl.rabobank.powerofattorney.stub.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CardIsBlockedException extends ResponseStatusException {
    public CardIsBlockedException(){
        super(HttpStatus.BAD_REQUEST, "Card is blocked");
    }
}
