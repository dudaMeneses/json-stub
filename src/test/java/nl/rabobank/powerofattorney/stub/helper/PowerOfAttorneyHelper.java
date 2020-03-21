package nl.rabobank.powerofattorney.stub.helper;

import lombok.experimental.UtilityClass;
import nl.rabobank.powerofattorney.stub.model.data.Authorization;
import nl.rabobank.powerofattorney.stub.model.data.Direction;
import nl.rabobank.powerofattorney.stub.model.data.Status;
import nl.rabobank.powerofattorney.stub.model.entity.PowerOfAttorney;
import nl.rabobank.powerofattorney.stub.model.entity.UserCard;

import java.util.List;
import java.util.Set;

@UtilityClass
public class PowerOfAttorneyHelper {
    public static PowerOfAttorney create(final String id) {
        var powerOfAttorney = new PowerOfAttorney();
        powerOfAttorney.setExternalId(id);
        powerOfAttorney.setAccount("NL23RABO123456789");
        powerOfAttorney.setAuthorizations(Set.of(Authorization.DEBIT_CARD, Authorization.PAYMENT, Authorization.VIEW));
        powerOfAttorney.setDirection(Direction.GIVEN);
        powerOfAttorney.setGrantee("Test Grantee");
        powerOfAttorney.setGrantor("Test Grantor");
        powerOfAttorney.setCards(Set.of(UserCard.of(CardHelper.credit(Status.ACTIVE)), UserCard.of(CardHelper.debit(Status.ACTIVE))));

        return powerOfAttorney;
    }

    public static Object unauthorized(final String id) {
        var powerOfAttorney = create(id);
        powerOfAttorney.setAuthorizations(Set.of());

        return powerOfAttorney;
    }
}
