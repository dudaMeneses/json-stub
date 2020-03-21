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
public class PowerOfAttorneyControllerFindAllTest {

    @Autowired
    private WebTestClient client;

    @Test
    public void whenHappyPath_shouldReturnAccount(){
        client.get().uri("/power-of-attorneys")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                    .jsonPath("$[0]").isEqualTo("0001")
                    .jsonPath("$[1]").isEqualTo("0002")
                    .jsonPath("$[2]").isEqualTo("0003")
                    .jsonPath("$[3]").isEqualTo("0004");
    }

}