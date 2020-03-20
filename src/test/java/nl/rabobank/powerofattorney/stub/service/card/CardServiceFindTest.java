package nl.rabobank.powerofattorney.stub.service.card;

import nl.rabobank.powerofattorney.stub.helper.CardHelper;
import nl.rabobank.powerofattorney.stub.model.data.CardType;
import nl.rabobank.powerofattorney.stub.model.data.Status;
import nl.rabobank.powerofattorney.stub.repository.CardRepository;
import nl.rabobank.powerofattorney.stub.service.CardService;
import nl.rabobank.powerofattorney.stub.service.exception.CardIsBlockedException;
import nl.rabobank.powerofattorney.stub.service.exception.CardNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
public class CardServiceFindTest {

    @InjectMocks
    private CardService cardService;

    @Mock
    private CardRepository cardRepository;

    @Test
    public void whenCardTypeIsDebitButCardIsCredit_shouldThrowCardNotFoundException(){
        doReturn(Mono.just(CardHelper.credit(Status.ACTIVE))).when(cardRepository).findById(anyLong());

        StepVerifier.create(cardService.find(CardType.DEBIT_CARD.getCardType(), 1L))
                .expectError(CardNotFoundException.class)
                .verify();
    }

    @Test
    public void whenCardTypeIsCreditButCardIsDebit_shouldThrowCardNotFoundException(){
        doReturn(Mono.just(CardHelper.debit(Status.ACTIVE))).when(cardRepository).findById(anyLong());

        StepVerifier.create(cardService.find(CardType.CREDIT_CARD.getCardType(), 1L))
                .expectError(CardNotFoundException.class)
                .verify();
    }

    @Test
    public void whenCardIsBlocked_shouldThrowCardIsBlockedException(){
        doReturn(Mono.just(CardHelper.credit(Status.BLOCKED))).when(cardRepository).findById(anyLong());

        StepVerifier.create(cardService.find(CardType.CREDIT_CARD.getCardType(), 1L))
                .expectError(CardIsBlockedException.class)
                .verify();
    }

    @Test
    public void whenHappyPath_shouldRetrieveTheCard(){
        doReturn(Mono.just(CardHelper.debit(Status.ACTIVE))).when(cardRepository).findById(anyLong());

        StepVerifier.create(cardService.find(CardType.DEBIT_CARD.getCardType(), 1L))
                .assertNext(card -> assertEquals(CardHelper.debit(Status.ACTIVE), card))
                .expectComplete()
                .verify();
    }

}