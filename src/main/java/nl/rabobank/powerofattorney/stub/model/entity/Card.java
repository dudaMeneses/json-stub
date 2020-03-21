package nl.rabobank.powerofattorney.stub.model.entity;

import lombok.Data;
import nl.rabobank.powerofattorney.stub.model.data.CardType;
import nl.rabobank.powerofattorney.stub.model.data.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Document(collection = "cards")
public abstract class Card {

    @Id
    private Long id;

    @NotNull
    @Positive
    private Long externalId;

    @NotNull
    protected Status status;

    @NotNull
    @Positive
    protected Long cardNumber;

    @NotNull
    @Positive
    protected Integer sequenceNumber;

    @NotBlank
    protected String cardHolder;

    private boolean contactless;

    public abstract CardType cardType();
}
