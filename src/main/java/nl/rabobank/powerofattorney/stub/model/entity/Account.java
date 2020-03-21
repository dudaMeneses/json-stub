package nl.rabobank.powerofattorney.stub.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "accounts")
public class Account {

    @Id
    private Long id;

    @NotNull
    @Positive
    private Long externalId;

    @NotNull
    private String owner;

    @NotNull
    private BigDecimal balance;

    @CreatedDate
    private LocalDate created;

    private LocalDate ended;

}
