package nl.rabobank.powerofattorney.stub.service.exception;

import nl.rabobank.powerofattorney.stub.model.entity.PowerOfAttorney;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PowerOfAttorneyNotFound extends ResponseStatusException {
    public PowerOfAttorneyNotFound(){
        super(HttpStatus.NOT_FOUND, "Power of attorney not found");
    }
}
