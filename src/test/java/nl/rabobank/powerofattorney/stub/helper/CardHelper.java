package nl.rabobank.powerofattorney.stub.helper;

import lombok.experimental.UtilityClass;
import nl.rabobank.powerofattorney.stub.model.data.Status;
import nl.rabobank.powerofattorney.stub.model.entity.Card;
import nl.rabobank.powerofattorney.stub.model.entity.CreditCard;
import nl.rabobank.powerofattorney.stub.model.entity.DebitCard;

import java.math.BigDecimal;

@UtilityClass
public class CardHelper {
    public static Card credit(Status status) {
        var creditCard = new CreditCard();
        creditCard.setId(10L);
        creditCard.setMonthlyLimit(BigDecimal.valueOf(3000.00));
        creditCard.setCardHolder("Test Owner");
        creditCard.setCardNumber(101010L);
        creditCard.setStatus(status);
        creditCard.setSequenceNumber(101);

        return creditCard;
    }

    public static Card debit(Status status) {
        var debitCard = new DebitCard();
        debitCard.setId(11L);
        debitCard.setCardHolder("Test Owner");
        debitCard.setCardNumber(111111L);
        debitCard.setStatus(status);
        debitCard.setSequenceNumber(111);
        debitCard.setAtmLimit(LimitHelper.atm());
        debitCard.setPosLimit(LimitHelper.pos());

        return debitCard;
    }
}
