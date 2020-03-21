package nl.rabobank.powerofattorney.stub.model.projections;

import io.swagger.annotations.Api;
import nl.rabobank.powerofattorney.stub.model.data.Authorization;
import nl.rabobank.powerofattorney.stub.model.data.Direction;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

@Api("PowerOfAttorney")
public interface PowerOfAttorneyProjection {
    @Value("#{target.externalId}")
    String getId();

    String getGrantor();
    String getGrantee();
    String getAccount();
    Direction getDirection();
    Set<Authorization> getAuthorizations();

    Set<UserCardProjection> getCards();
}
