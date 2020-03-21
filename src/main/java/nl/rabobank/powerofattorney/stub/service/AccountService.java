package nl.rabobank.powerofattorney.stub.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.stub.model.entity.Account;
import nl.rabobank.powerofattorney.stub.repository.AccountRepository;
import nl.rabobank.powerofattorney.stub.service.exception.AccountClosedException;
import nl.rabobank.powerofattorney.stub.service.exception.AccountNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class AccountService {

    @NonNull
    private AccountRepository repository;

    public Mono<Account> findByExternalId(final String externalId) {
        return repository.findByExternalId(externalId)
                .switchIfEmpty(Mono.error(new AccountNotFoundException()))
                .filter(validateAccountClosed())
                .switchIfEmpty(Mono.error(new AccountClosedException()));
    }

    private Predicate<Account> validateAccountClosed() {
        return account -> account.getEnded() == null;
    }
}
