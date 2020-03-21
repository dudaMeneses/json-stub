package nl.rabobank.powerofattorney.stub.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.stub.model.data.Authorization;
import nl.rabobank.powerofattorney.stub.model.data.CardType;
import nl.rabobank.powerofattorney.stub.model.data.Status;
import nl.rabobank.powerofattorney.stub.model.entity.Card;
import nl.rabobank.powerofattorney.stub.model.entity.PowerOfAttorney;
import nl.rabobank.powerofattorney.stub.model.entity.UserCard;
import nl.rabobank.powerofattorney.stub.repository.CreditCardRepository;
import nl.rabobank.powerofattorney.stub.repository.DebitCardRepository;
import nl.rabobank.powerofattorney.stub.service.exception.CardIsBlockedException;
import nl.rabobank.powerofattorney.stub.service.exception.CardNotFoundException;
import nl.rabobank.powerofattorney.stub.service.exception.PowerOfAttorneyNotFound;
import nl.rabobank.powerofattorney.stub.service.exception.UserNotAuthorizedException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class CardService implements GrantVerification<Card> {

    @NonNull
    private DebitCardRepository debitCardRepository;

    @NonNull
    private CreditCardRepository creditCardRepository;

    @NonNull
    private PowerOfAttorneyService powerOfAttorneyService;

    public Mono<? extends Card> find(final String cardType, final String externalId) {
        return findCard(cardType, externalId)
                    .switchIfEmpty(Mono.error(new CardNotFoundException()))
                    .flatMap(this::validateOwnerGrant)
                    .filter(validateCardStatus())
                    .switchIfEmpty(Mono.error(new CardIsBlockedException()));
    }

    @Override
    public Mono<Card> validateOwnerGrant(Card card) {
        return powerOfAttorneyService.findCardOwner(UserCard.of(card))
                .switchIfEmpty(Mono.error(PowerOfAttorneyNotFound::new))
                .filter(cardTypeAuthorizationPredicate(card))
                .switchIfEmpty(Mono.error(new UserNotAuthorizedException(Authorization.find(card.cardType()))))
                .then(Mono.just(card));
    }

    private Mono<? extends Card> findCard(String cardType, String externalId) {
        if(CardType.CREDIT_CARD.getCardType().equalsIgnoreCase(cardType)){
            return creditCardRepository.findByExternalId(externalId);
        } else {
            return debitCardRepository.findByExternalId(externalId);
        }
    }

    private Predicate<PowerOfAttorney> cardTypeAuthorizationPredicate(Card card) {
        return powerOfAttorney -> powerOfAttorney.getAuthorizations().contains(Authorization.find(card.cardType()));
    }

    private Predicate<Card> validateCardStatus() {
        return card -> card.getStatus().equals(Status.ACTIVE);
    }

}
