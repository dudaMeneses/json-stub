package nl.rabobank.powerofattorney.stub.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.stub.model.entity.Account;
import nl.rabobank.powerofattorney.stub.repository.AccountRepository;
import nl.rabobank.powerofattorney.stub.service.exception.AccountNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountService {

    @NonNull
    private AccountRepository repository;

    public Mono<Account> findById(final Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new AccountNotFoundException()));
    }
}
