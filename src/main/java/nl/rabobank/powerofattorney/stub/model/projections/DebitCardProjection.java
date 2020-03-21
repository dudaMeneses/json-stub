package nl.rabobank.powerofattorney.stub.model.projections;

import io.swagger.annotations.Api;

@Api("DebitCard")
public interface DebitCardProjection extends CardProjection {
    LimitProjection getAtmLimit();
    LimitProjection getPosLimit();
    boolean isContactless();
}
