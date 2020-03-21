package nl.rabobank.powerofattorney.stub.model.entity;

import lombok.Data;
import nl.rabobank.powerofattorney.stub.model.data.CardType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UserCard {

    @NotNull
    @Positive
    private String externalId;

    @NotNull
    private CardType type;
}
