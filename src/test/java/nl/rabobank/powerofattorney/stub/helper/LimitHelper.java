package nl.rabobank.powerofattorney.stub.helper;

import lombok.experimental.UtilityClass;
import nl.rabobank.powerofattorney.stub.model.data.PeriodUnit;
import nl.rabobank.powerofattorney.stub.model.entity.Limit;

import java.math.BigDecimal;

@UtilityClass
public class LimitHelper {
    public static Limit atm() {
        var limit = new Limit();
        limit.setLimit(BigDecimal.valueOf(500.00));
        limit.setPeriodUnit(PeriodUnit.PER_DAY);

        return limit;
    }

    public static Limit pos() {
        var limit = new Limit();
        limit.setLimit(BigDecimal.valueOf(1000.00));
        limit.setPeriodUnit(PeriodUnit.PER_MONTH);

        return limit;
    }
}
