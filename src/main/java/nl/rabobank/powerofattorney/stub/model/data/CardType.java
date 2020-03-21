package nl.rabobank.powerofattorney.stub.model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardType {
    CREDIT_CARD("credit"),
    DEBIT_CARD("debit");

    private String cardType;
}
