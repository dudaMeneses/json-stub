package nl.rabobank.powerofattorney.stub.helper;

import lombok.experimental.UtilityClass;
import nl.rabobank.powerofattorney.stub.model.data.Authorization;
import nl.rabobank.powerofattorney.stub.model.data.Direction;
import nl.rabobank.powerofattorney.stub.model.data.Status;
import nl.rabobank.powerofattorney.stub.model.entity.PowerOfAttorney;

import java.util.Set;

@UtilityClass
public class PowerOfAttorneyHelper {
    public static PowerOfAttorney create(final Long id) {
        var powerOfAttorney = new PowerOfAttorney();
        powerOfAttorney.setId(id);
        powerOfAttorney.setAccount("NL23RABO123456789");
        powerOfAttorney.setAuthorizations(Set.of(Authorization.DEBIT_CARD, Authorization.PAYMENT, Authorization.VIEW));
        powerOfAttorney.setCards(Set.of(CardHelper.debit(Status.ACTIVE), CardHelper.credit(Status.ACTIVE)));
        powerOfAttorney.setDirection(Direction.GIVEN);
        powerOfAttorney.setGrantee("Test Grantee");
        powerOfAttorney.setGrantor("Test Grantor");

        return powerOfAttorney;
    }
}
