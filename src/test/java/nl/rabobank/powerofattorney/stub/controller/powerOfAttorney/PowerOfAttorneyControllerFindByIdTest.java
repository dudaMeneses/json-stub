package nl.rabobank.powerofattorney.stub.controller.powerOfAttorney;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PowerOfAttorneyControllerFindByIdTest {

    @Autowired
    private WebTestClient client;

    @Test
    public void whenHappyPath_shouldReturnAccount(){
        client.get().uri("/power-of-attorneys/{id}", "0001")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("0001")
                .jsonPath("$.grantor").isEqualTo("Super duper company")
                .jsonPath("$.grantee").isEqualTo("Fellowship of the ring")
                .jsonPath("$.account").isEqualTo("NL23RABO123456789")
                .jsonPath("$.direction").isEqualTo("GIVEN")
                .jsonPath("$.authorizations[0]").isEqualTo("DEBIT_CARD")
                .jsonPath("$.authorizations[1]").isEqualTo("VIEW")
                .jsonPath("$.authorizations[2]").isEqualTo("PAYMENT")
                .jsonPath("$.cards[0].id").isEqualTo("1111")
                .jsonPath("$.cards[0].type").isEqualTo("DEBIT_CARD")
                .jsonPath("$.cards[1].id").isEqualTo("2222")
                .jsonPath("$.cards[1].type").isEqualTo("DEBIT_CARD")
                .jsonPath("$.cards[2].id").isEqualTo("3333")
                .jsonPath("$.cards[2].type").isEqualTo("CREDIT_CARD");
    }

    @Test
    public void whenNotFound_shouldReturnNotFound(){
        client.get().uri("/power-of-attorneys/{id}", "9999")
                .exchange()
                .expectStatus().isNotFound();
    }

}