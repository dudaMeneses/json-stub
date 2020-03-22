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
                    .flatMap(this::validateCardStatus);
    }

    @Override
    public Mono<Card> validateOwnerGrant(Card card) {
        return powerOfAttorneyService.findCardOwner(UserCard.of(card))
                .switchIfEmpty(Mono.error(PowerOfAttorneyNotFound::new))
                .flatMap(powerOfAttorney -> cardTypeAuthorizationPredicate(powerOfAttorney, card))
                .then(Mono.just(card));
    }

    private Mono<? extends Card> findCard(String cardType, String externalId) {
        if(CardType.CREDIT_CARD.getCardType().equalsIgnoreCase(cardType)){
            return creditCardRepository.findByExternalId(externalId);
        } else {
            return debitCardRepository.findByExternalId(externalId);
        }
    }

    private Mono<Card> validateCardStatus(Card card) {
        return Mono.just(card)
                .filter(c -> c.getStatus().equals(Status.ACTIVE))
                .switchIfEmpty(Mono.error(new CardIsBlockedException()));
    }

    private Mono<PowerOfAttorney> cardTypeAuthorizationPredicate(PowerOfAttorney powerOfAttorney, Card card) {
        return Mono.just(powerOfAttorney)
                .filter(p -> p.getAuthorizations().contains(Authorization.find(card.cardType())))
                .switchIfEmpty(Mono.error(new UserNotAuthorizedException(Authorization.find(card.cardType()))));
    }

}
