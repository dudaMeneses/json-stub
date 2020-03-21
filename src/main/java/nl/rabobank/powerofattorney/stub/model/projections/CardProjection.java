package nl.rabobank.powerofattorney.stub.model.projections;

import io.swagger.annotations.Api;
import nl.rabobank.powerofattorney.stub.model.data.Status;
import org.springframework.beans.factory.annotation.Value;

@Api("Card")
public interface CardProjection {
    @Value("#{target.externalId}")
    String getId();

    Status getStatus();

    Integer getCardNumber();
    Integer getSequenceNumber();
    String getCardHolder();
    boolean isContactless();
}
