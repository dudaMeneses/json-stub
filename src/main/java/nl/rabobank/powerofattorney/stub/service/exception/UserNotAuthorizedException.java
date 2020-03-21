package nl.rabobank.powerofattorney.stub.service.exception;

import nl.rabobank.powerofattorney.stub.model.data.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotAuthorizedException extends ResponseStatusException {
    public UserNotAuthorizedException(Authorization authorization) {
        super(HttpStatus.FORBIDDEN, String.format("User not authorized to %s", authorization));
    }
}
