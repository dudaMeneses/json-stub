package nl.rabobank.powerofattorney.stub.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AccountClosedException extends ResponseStatusException {
    public AccountClosedException(){
        super(HttpStatus.BAD_REQUEST, "Account is already closed");
    }
}
