package nl.rabobank.powerofattorney.stub.model.projections;

import io.swagger.annotations.ApiModel;
import nl.rabobank.powerofattorney.stub.model.data.CardType;
import org.springframework.beans.factory.annotation.Value;

@ApiModel("UserCard")
public interface UserCardProjection {
    @Value("#{target.externalId}")
    String getId();

    CardType getType();
}
