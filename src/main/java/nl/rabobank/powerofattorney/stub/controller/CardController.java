package nl.rabobank.powerofattorney.stub.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.stub.model.entity.Card;
import nl.rabobank.powerofattorney.stub.service.CardService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/{cardType}-card", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    @NonNull
    private CardService service;

    @GetMapping(path = "/{id}")
    public Mono<Card> findById(@PathVariable String cardType,
                               @PathVariable Long id){
        return service.find(cardType, id);
    }
}
