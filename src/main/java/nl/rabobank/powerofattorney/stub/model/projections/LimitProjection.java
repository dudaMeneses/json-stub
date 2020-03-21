package nl.rabobank.powerofattorney.stub.model.projections;

import io.swagger.annotations.Api;
import nl.rabobank.powerofattorney.stub.model.data.PeriodUnit;

import java.math.BigDecimal;

@Api("Limit")
public interface LimitProjection {
    BigDecimal getLimit();
    PeriodUnit getPeriodUnit();
}
