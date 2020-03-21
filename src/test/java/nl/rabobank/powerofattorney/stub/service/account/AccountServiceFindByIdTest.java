package nl.rabobank.powerofattorney.stub.service.account;

import nl.rabobank.powerofattorney.stub.helper.AccountHelper;
import nl.rabobank.powerofattorney.stub.repository.AccountRepository;
import nl.rabobank.powerofattorney.stub.service.AccountService;
import nl.rabobank.powerofattorney.stub.service.exception.AccountNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AccountServiceFindByIdTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void whenNotFound_shouldReturnAccountNotFoundException(){
        doReturn(Mono.empty()).when(accountRepository).findByExternalId(anyLong());

        StepVerifier.create(accountService.findByExternalId(1L))
                .expectError(AccountNotFoundException.class)
                .verify();
    }

    @Test
    public void whenHappyPath_shouldReturnAccountNotFoundException(){
        doReturn(Mono.just(AccountHelper.create())).when(accountRepository).findByExternalId(anyLong());

        StepVerifier.create(accountService.findByExternalId(1L))
                .assertNext(account -> assertEquals(AccountHelper.create(), account))
                .expectComplete()
                .verify();
    }

}