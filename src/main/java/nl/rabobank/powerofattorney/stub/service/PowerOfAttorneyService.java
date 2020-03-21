package nl.rabobank.powerofattorney.stub.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.stub.model.entity.PowerOfAttorney;
import nl.rabobank.powerofattorney.stub.repository.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.stub.service.exception.PowerOfAttorneyNotFound;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
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

    public Mono<PowerOfAttorney> findById(final Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new PowerOfAttorneyNotFound()));
    }
}
