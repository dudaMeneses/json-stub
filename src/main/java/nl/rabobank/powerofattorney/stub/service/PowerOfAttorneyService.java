package nl.rabobank.powerofattorney.stub.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.stub.model.entity.PowerOfAttorney;
import nl.rabobank.powerofattorney.stub.model.entity.UserCard;
import nl.rabobank.powerofattorney.stub.repository.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.stub.service.exception.PowerOfAttorneyNotFound;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PowerOfAttorneyService {

    @NonNull
    private PowerOfAttorneyRepository repository;

    public Mono<List<String>> findAllIds() {
        return repository.findAll()
                .map(PowerOfAttorney::getExternalId)
                .collectList();
    }

    public Mono<PowerOfAttorney> findByExternalId(final String id) {
        return repository.findByExternalId(id)
                .switchIfEmpty(Mono.error(new PowerOfAttorneyNotFound()));
    }

    public Mono<PowerOfAttorney> findAccountOwner(String account) {
        return repository.findByAccount(account);
    }

    public Mono<PowerOfAttorney> findCardOwner(UserCard userCard) {
        return repository.findByCards(userCard);
    }
}
