package nl.rabobank.powerofattorney.stub.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.stub.model.data.Status;
import nl.rabobank.powerofattorney.stub.model.entity.Card;
import nl.rabobank.powerofattorney.stub.repository.CardRepository;
import nl.rabobank.powerofattorney.stub.service.exception.CardIsBlockedException;
import nl.rabobank.powerofattorney.stub.service.exception.CardNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class CardService {

    @NonNull
    private CardRepository repository;

    public Mono<Card> find(final String cardType, final Long id) {
        return repository.findById(id)
                    .filter(validateCardType(cardType))
                    .switchIfEmpty(Mono.error(new CardNotFoundException()))
                    .filter(validateCardStatus())
                    .switchIfEmpty(Mono.error(new CardIsBlockedException()));
    }

    private Predicate<Card> validateCardStatus() {
        return card -> card.getStatus().equals(Status.ACTIVE);
    }

    private Predicate<Card> validateCardType(String cardType) {
        return card -> card.cardType().getCardType().equals(cardType.toLowerCase());
    }
}
