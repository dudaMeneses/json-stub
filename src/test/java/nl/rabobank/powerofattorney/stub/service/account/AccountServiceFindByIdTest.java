package nl.rabobank.powerofattorney.stub.service.account;

import nl.rabobank.powerofattorney.stub.helper.AccountHelper;
import nl.rabobank.powerofattorney.stub.helper.PowerOfAttorneyHelper;
import nl.rabobank.powerofattorney.stub.repository.AccountRepository;
import nl.rabobank.powerofattorney.stub.service.AccountService;
import nl.rabobank.powerofattorney.stub.service.PowerOfAttorneyService;
import nl.rabobank.powerofattorney.stub.service.exception.AccountClosedException;
import nl.rabobank.powerofattorney.stub.service.exception.AccountNotFoundException;
import nl.rabobank.powerofattorney.stub.service.exception.UserNotAuthorizedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AccountServiceFindByIdTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PowerOfAttorneyService powerOfAttorneyService;

    @Test
    public void whenNotFound_shouldThrowAccountNotFoundException(){
        doReturn(Mono.empty()).when(accountRepository).findByExternalId(anyString());

        StepVerifier.create(accountService.findByExternalId("1L"))
                .expectError(AccountNotFoundException.class)
                .verify();
    }

    @Test
    public void whenAccountIsEnded_shouldThrowAccountClosedException(){
        doReturn(Mono.just(PowerOfAttorneyHelper.create("1L"))).when(powerOfAttorneyService).findAccountOwner(anyString());
        doReturn(Mono.just(AccountHelper.ended())).when(accountRepository).findByExternalId(anyString());

        StepVerifier.create(accountService.findByExternalId("1L"))
                .expectError(AccountClosedException.class)
                .verify();
    }

    @Test
    public void whenHasNoGrantForAccount_shouldThrowUserNotAuthorizedException(){
        doReturn(Mono.just(PowerOfAttorneyHelper.unauthorized("1L"))).when(powerOfAttorneyService).findAccountOwner(anyString());
        doReturn(Mono.just(AccountHelper.create())).when(accountRepository).findByExternalId(anyString());

        StepVerifier.create(accountService.findByExternalId("1L"))
                .expectError(UserNotAuthorizedException.class)
                .verify();
    }

    @Test
    public void whenHappyPath_shouldReturnAccount(){
        doReturn(Mono.just(PowerOfAttorneyHelper.create("1L"))).when(powerOfAttorneyService).findAccountOwner(anyString());
        doReturn(Mono.just(AccountHelper.create())).when(accountRepository).findByExternalId(anyString());

        StepVerifier.create(accountService.findByExternalId("1L"))
                .assertNext(account -> assertEquals(AccountHelper.create(), account))
                .expectComplete()
                .verify();
    }

}