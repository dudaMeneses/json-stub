package nl.rabobank.powerofattorney.stub.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.stub.model.data.CardType;
import nl.rabobank.powerofattorney.stub.model.entity.Card;
import nl.rabobank.powerofattorney.stub.model.projections.AccountProjection;
import nl.rabobank.powerofattorney.stub.model.projections.CardProjection;
import nl.rabobank.powerofattorney.stub.model.projections.CreditCardProjection;
import nl.rabobank.powerofattorney.stub.model.projections.DebitCardProjection;
import nl.rabobank.powerofattorney.stub.service.CardService;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Api(tags = "cards")
@RequestMapping(path = "/{cardType}-card", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    @NonNull
    private CardService service;

    @NonNull
    private SpelAwareProxyProjectionFactory projectionFactory;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Find cards by its specific id",
            response = CardProjection.class,
            responseContainer = "Mono"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Card found"),
            @ApiResponse(code = 404, message = "Card not found"),
            @ApiResponse(code = 400, message = "Card is blocked")
    })
    public Mono<CardProjection> findById(@PathVariable String cardType,
                               @PathVariable Long id){
        return service.find(cardType, id)
                .map(card -> {
                   if(CardType.CREDIT_CARD.equals(card.cardType())){
                       return projectionFactory.createProjection(CreditCardProjection.class, card);
                   } else {
                       return projectionFactory.createProjection(DebitCardProjection.class, card);
                   }
                });
    }
}
