package nl.rabobank.powerofattorney.stub.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rabobank.powerofattorney.stub.model.data.CardType;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@TypeAlias("creditCard")
@Document(collection = "cards")
@EqualsAndHashCode(callSuper = true)
public class CreditCard extends Card {
    private BigDecimal monthlyLimit;

    @Override
    public CardType cardType() {
        return CardType.CREDIT_CARD;
    }
}
