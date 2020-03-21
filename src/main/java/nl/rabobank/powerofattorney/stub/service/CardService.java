package nl.rabobank.powerofattorney.stub.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.stub.model.data.CardType;
import nl.rabobank.powerofattorney.stub.model.data.Status;
import nl.rabobank.powerofattorney.stub.model.entity.Card;
import nl.rabobank.powerofattorney.stub.model.entity.CreditCard;
import nl.rabobank.powerofattorney.stub.model.entity.DebitCard;
import nl.rabobank.powerofattorney.stub.repository.CreditCardRepository;
import nl.rabobank.powerofattorney.stub.repository.DebitCardRepository;
import nl.rabobank.powerofattorney.stub.service.exception.CardIsBlockedException;
import nl.rabobank.powerofattorney.stub.service.exception.CardNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class CardService {

    @NonNull
    private DebitCardRepository debitCardRepository;

    @NonNull
    private CreditCardRepository creditCardRepository;

    public Mono<? extends Card> find(final String cardType, final String externalId) {
        return findCard(cardType, externalId)
                    .switchIfEmpty(Mono.error(new CardNotFoundException()))
                    .filter(validateCardStatus())
                    .switchIfEmpty(Mono.error(new CardIsBlockedException()));
    }

    private Mono<? extends Card> findCard(String cardType, String externalId) {
        if(CardType.CREDIT_CARD.getCardType().equalsIgnoreCase(cardType)){
            return creditCardRepository.findByExternalId(externalId);
        } else {
            return debitCardRepository.findByExternalId(externalId);
        }
    }

    private Predicate<Card> validateCardStatus() {
        return card -> card.getStatus().equals(Status.ACTIVE);
    }

}
