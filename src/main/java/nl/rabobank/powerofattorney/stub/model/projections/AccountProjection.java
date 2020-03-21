package nl.rabobank.powerofattorney.stub.model.projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@ApiModel("Account")
public interface AccountProjection {
    @Value("#{target.externalId}")
    String getId();

    String getOwner();
    BigDecimal getBalance();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate getCreated();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate getEnded();
}
