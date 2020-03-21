package nl.rabobank.powerofattorney.stub.controller.cards;

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
public class CardControllerFindByIdTest {

    @Autowired
    private WebTestClient client;

    @Test
    public void whenDebitHappyPath_shouldReturnDebitCard(){
        client.get().uri("/{cardType}-cards/{id}", "debit", "1111")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                    .jsonPath("$.id").isEqualTo("1111")
                    .jsonPath("$.status").isEqualTo("ACTIVE")
                    .jsonPath("$.cardNumber").isEqualTo("1234")
                    .jsonPath("$.sequenceNumber").isEqualTo("5")
                    .jsonPath("$.cardHolder").isEqualTo("Frodo Basggins")
                    .jsonPath("$.atmLimit.limit").isEqualTo("3000")
                    .jsonPath("$.atmLimit.periodUnit").isEqualTo("PER_WEEK")
                    .jsonPath("$.posLimit.limit").isEqualTo("50")
                    .jsonPath("$.posLimit.periodUnit").isEqualTo("PER_MONTH")
                    .jsonPath("$.contactless").isEqualTo("true");
    }

    @Test
    public void whenCreditHappyPath_shouldReturnDebitCard(){
        client.get().uri("/{cardType}-cards/{id}", "credit", "3333")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("3333")
                .jsonPath("$.status").isEqualTo("ACTIVE")
                .jsonPath("$.cardNumber").isEqualTo("5075")
                .jsonPath("$.sequenceNumber").isEqualTo("1")
                .jsonPath("$.cardHolder").isEqualTo("Boromir")
                .jsonPath("$.monthlyLimit").isEqualTo("3000");
    }

    @Test
    public void whenNotFound_shouldReturnNotFound(){
        client.get().uri("/{cardType}-cards/{id}", "debit", "9999999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void whenCardIsBlocked_shouldReturnBadRequest(){
        client.get().uri("/{cardType}-cards/{id}", "debit", "5555")
                .exchange()
                .expectStatus().isBadRequest();
    }

}