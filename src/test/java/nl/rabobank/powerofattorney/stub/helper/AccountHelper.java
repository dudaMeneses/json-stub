package nl.rabobank.powerofattorney.stub.helper;

import lombok.experimental.UtilityClass;
import nl.rabobank.powerofattorney.stub.model.entity.Account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

@UtilityClass
public class AccountHelper {

    public static Account create() {
        var account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(1000.00));
        account.setCreated(LocalDate.of(2020, Month.MARCH, 20));
        account.setOwner("Test Owner");

        return account;
    }
}
