package nl.rabobank.powerofattorney.stub.repository;

import nl.rabobank.powerofattorney.stub.model.entity.PowerOfAttorney;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PowerOfAttorneyRepository extends ReactiveMongoRepository<PowerOfAttorney, Long> {
    Mono<PowerOfAttorney> findByExternalId(String id);
}
