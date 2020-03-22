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
                .flatMap(this::validateAccountClosed);
    }

    public Mono<Account> validateOwnerGrant(Account account) {
        return powerOfAttorneyService.findAccountOwner(account.getExternalId())
                .switchIfEmpty(Mono.error(PowerOfAttorneyNotFound::new))
                .flatMap(this::validateGrant)
                .then(Mono.just(account));
    }

    private Mono<PowerOfAttorney> validateGrant(PowerOfAttorney powerOfAttorney) {
        return Mono.just(powerOfAttorney)
                .filter(p -> p.getAuthorizations().contains(Authorization.VIEW))
                .switchIfEmpty(Mono.error(new UserNotAuthorizedException(Authorization.VIEW)));
    }

    private Mono<Account> validateAccountClosed(Account account) {
        return Mono.just(account)
                .filter(a -> a.getEnded() == null)
                .switchIfEmpty(Mono.error(new AccountClosedException()));
    }
}
