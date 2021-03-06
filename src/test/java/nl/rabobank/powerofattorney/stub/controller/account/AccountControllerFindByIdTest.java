package nl.rabobank.powerofattorney.stub.controller.account;

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
public class AccountControllerFindByIdTest {

    @Autowired
    private WebTestClient client;

    @Test
    public void whenHappyPath_shouldReturnAccount(){
        client.get().uri("/accounts/{id}", "NL23RABO123123123")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                    .jsonPath("$.id").isEqualTo("NL23RABO123123123")
                    .jsonPath("$.balance").isEqualTo("-125")
                    .jsonPath("$.created").isEqualTo("12-10-2007")
                    .jsonPath("$.owner").isEqualTo("uper duper employee");
    }

    @Test
    public void whenNotFound_shouldReturnNotFound(){
        client.get().uri("/accounts/{id}", "NL23RABO999999999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void whenAccountIsClosed_shouldReturnBadRequest(){
        client.get().uri("/accounts/{id}", "NL23RABO987654321")
                .exchange()
                .expectStatus().isBadRequest();
    }

}