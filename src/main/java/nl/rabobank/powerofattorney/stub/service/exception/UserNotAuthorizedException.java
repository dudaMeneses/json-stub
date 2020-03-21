package nl.rabobank.powerofattorney.stub.service.exception;

import nl.rabobank.powerofattorney.stub.model.data.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotAuthorizedException extends ResponseStatusException {
    public UserNotAuthorizedException(Authorization authorization) {
        super(HttpStatus.BAD_REQUEST, String.format("User not authorized to %s", authorization));
    }
}
