package nl.rabobank.powerofattorney.stub.model.entity;

import lombok.Data;
import nl.rabobank.powerofattorney.stub.model.data.PeriodUnit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class Limit {

    @Positive
    private BigDecimal limit;

    @NotNull
    private PeriodUnit periodUnit;
}
