package nl.rabobank.powerofattorney.stub.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rabobank.powerofattorney.stub.model.data.CardType;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document(collection = "cards")
@EqualsAndHashCode(callSuper = true)
public class DebitCard extends Card {

    @NotNull
    private Limit atmLimit;

    @NotNull
    private Limit posLimit;

    @Override
    public CardType cardType() {
        return CardType.DEBIT_CARD;
    }
}
