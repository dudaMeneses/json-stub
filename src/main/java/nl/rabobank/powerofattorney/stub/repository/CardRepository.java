package nl.rabobank.powerofattorney.stub.repository;

import nl.rabobank.powerofattorney.stub.model.entity.Card;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends ReactiveMongoRepository<Card, Long> {
}
