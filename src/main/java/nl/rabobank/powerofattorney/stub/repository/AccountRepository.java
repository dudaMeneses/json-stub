package nl.rabobank.powerofattorney.stub.repository;

import nl.rabobank.powerofattorney.stub.model.entity.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, Long> {
}
