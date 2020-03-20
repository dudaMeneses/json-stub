package nl.rabobank.powerofattorney.stub.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CardNotFoundException extends ResponseStatusException {
    public CardNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Card not found");
    }
}
