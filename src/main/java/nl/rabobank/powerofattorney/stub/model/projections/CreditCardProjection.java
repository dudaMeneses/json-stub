package nl.rabobank.powerofattorney.stub.model.projections;

import io.swagger.annotations.Api;

import java.math.BigDecimal;

@Api("CreditCard")
public interface CreditCardProjection extends CardProjection {
    BigDecimal getMonthlyLimit();
}
