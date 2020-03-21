package nl.rabobank.powerofattorney.stub.service.card;

import nl.rabobank.powerofattorney.stub.helper.CardHelper;
import nl.rabobank.powerofattorney.stub.helper.PowerOfAttorneyHelper;
import nl.rabobank.powerofattorney.stub.model.data.CardType;
import nl.rabobank.powerofattorney.stub.model.data.Status;
import nl.rabobank.powerofattorney.stub.model.entity.DebitCard;
import nl.rabobank.powerofattorney.stub.model.entity.UserCard;
import nl.rabobank.powerofattorney.stub.repository.CreditCardRepository;
import nl.rabobank.powerofattorney.stub.repository.DebitCardRepository;
import nl.rabobank.powerofattorney.stub.service.CardService;
import nl.rabobank.powerofattorney.stub.service.PowerOfAttorneyService;
import nl.rabobank.powerofattorney.stub.service.exception.CardIsBlockedException;
import nl.rabobank.powerofattorney.stub.service.exception.CardNotFoundException;
import nl.rabobank.powerofattorney.stub.service.exception.UserNotAuthorizedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class CardServiceFindTest {

    @InjectMocks
    private CardService cardService;

    @Mock
    private CreditCardRepository creditCardRepository;

    @Mock
    private DebitCardRepository debitCardRepository;

    @Mock
    private PowerOfAttorneyService powerOfAttorneyService;

    @Test
    public void whenDebitCardNotFound_shouldThrowCardNotFoundException(){
        doReturn(Mono.empty()).when(debitCardRepository).findByExternalId(anyString());

        StepVerifier.create(cardService.find(CardType.DEBIT_CARD.getCardType(), "1L"))
                .expectError(CardNotFoundException.class)
                .verify();
    }

    @Test
    public void whenCreditCardNotFound_shouldThrowCardNotFoundException(){
        doReturn(Mono.empty()).when(creditCardRepository).findByExternalId(anyString());

        StepVerifier.create(cardService.find(CardType.CREDIT_CARD.getCardType(), "1L"))
                .expectError(CardNotFoundException.class)
                .verify();
    }

    @Test
    public void whenCardIsBlocked_shouldThrowCardIsBlockedException(){
        doReturn(Mono.just(PowerOfAttorneyHelper.create("1L"))).when(powerOfAttorneyService).findCardOwner(any(UserCard.class));
        doReturn(Mono.just(CardHelper.credit(Status.BLOCKED))).when(creditCardRepository).findByExternalId(anyString());

        StepVerifier.create(cardService.find(CardType.CREDIT_CARD.getCardType(), "1L"))
                .expectError(CardIsBlockedException.class)
                .verify();
    }

    @Test
    public void whenDebitHappyPath_shouldRetrieveDebitCard(){
        doReturn(Mono.just(PowerOfAttorneyHelper.create("1L"))).when(powerOfAttorneyService).findCardOwner(any(UserCard.class));
        doReturn(Mono.just(CardHelper.debit(Status.ACTIVE))).when(debitCardRepository).findByExternalId(anyString());

        StepVerifier.create(cardService.find(CardType.DEBIT_CARD.getCardType(), "1L"))
                .assertNext(card -> assertEquals(CardHelper.debit(Status.ACTIVE), card))
                .expectComplete()
                .verify();
    }

    @Test
    public void whenCreditHappyPath_shouldRetrieveCreditCard(){
        doReturn(Mono.just(PowerOfAttorneyHelper.create("1L"))).when(powerOfAttorneyService).findCardOwner(any(UserCard.class));
        doReturn(Mono.just(CardHelper.credit(Status.ACTIVE))).when(creditCardRepository).findByExternalId(anyString());

        StepVerifier.create(cardService.find(CardType.CREDIT_CARD.getCardType(), "1L"))
                .assertNext(card -> assertEquals(CardHelper.credit(Status.ACTIVE), card))
                .expectComplete()
                .verify();
    }

    @Test
    public void whenUserNotAuthorized_shouldThrowUserNotAuthorizedException(){
        doReturn(Mono.just(PowerOfAttorneyHelper.unauthorized("1L"))).when(powerOfAttorneyService).findCardOwner(any(UserCard.class));
        doReturn(Mono.just(CardHelper.credit(Status.ACTIVE))).when(creditCardRepository).findByExternalId(anyString());

        StepVerifier.create(cardService.find(CardType.CREDIT_CARD.getCardType(), "1L"))
                .expectError(UserNotAuthorizedException.class)
                .verify();
    }

}