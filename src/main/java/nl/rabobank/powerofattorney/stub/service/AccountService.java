package nl.rabobank.powerofattorney.stub.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.stub.model.data.Authorization;
import nl.rabobank.powerofattorney.stub.model.entity.Account;
import nl.rabobank.powerofattorney.stub.model.entity.PowerOfAttorney;
import nl.rabobank.powerofattorney.stub.repository.AccountRepository;
import nl.rabobank.powerofattorney.stub.service.exception.AccountClosedException;
import nl.rabobank.powerofattorney.stub.service.exception.AccountNotFoundException;
import nl.rabobank.powerofattorney.stub.service.exception.PowerOfAttorneyNotFound;
import nl.rabobank.powerofattorney.stub.service.exception.UserNotAuthorizedException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class AccountService implements GrantVerification<Account> {

    @NonNull
    private AccountRepository repository;

    @NonNull
    private PowerOfAttorneyService powerOfAttorneyService;

    public Mono<Account> findByExternalId(final String externalId) {
        return repository.findByExternalId(externalId)
                .switchIfEmpty(Mono.error(new AccountNotFoundException()))
                .flatMap(this::validateOwnerGrant)
                .filter(validateAccountClosed())
                .switchIfEmpty(Mono.error(new AccountClosedException()));
    }

    public Mono<Account> validateOwnerGrant(Account account) {
        return powerOfAttorneyService.findAccountOwner(account.getExternalId())
                .switchIfEmpty(Mono.error(PowerOfAttorneyNotFound::new))
                .filter(powerOfAttorney -> powerOfAttorney.getAuthorizations().contains(Authorization.VIEW))
                .switchIfEmpty(Mono.error(new UserNotAuthorizedException(Authorization.VIEW)))
                .then(Mono.just(account));
    }

    private Predicate<Account> validateAccountClosed() {
        return account -> account.getEnded() == null;
    }
}
