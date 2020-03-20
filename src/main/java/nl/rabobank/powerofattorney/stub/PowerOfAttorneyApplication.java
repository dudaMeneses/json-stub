package nl.rabobank.powerofattorney.stub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication(exclude = EmbeddedMongoAutoConfiguration.class)
public class PowerOfAttorneyApplication {
    public static void main(String[] args) {
        SpringApplication.run(PowerOfAttorneyApplication.class);
    }
}
