package nl.rabobank.powerofattorney.stub.service;

import reactor.core.publisher.Mono;

public interface GrantVerification<T> {
    Mono<T> validateOwnerGrant(T entity);
}
