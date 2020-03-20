package nl.rabobank.powerofattorney.stub.model.entity;

import lombok.Data;
import nl.rabobank.powerofattorney.stub.model.data.Authorization;
import nl.rabobank.powerofattorney.stub.model.data.Direction;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Document(collection = "poas")
public class PowerOfAttorney {

    @Id
    private Long id;

    @NotBlank
    private String grantor;

    @NotBlank
    private String grantee;

    @NotBlank
    private String account;

    @NotNull
    private Direction direction;

    @NotEmpty
    private Set<Authorization> authorizations;

    private Set<Card> cards;

}
