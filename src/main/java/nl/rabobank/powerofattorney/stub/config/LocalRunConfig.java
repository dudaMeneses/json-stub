package nl.rabobank.powerofattorney.stub.config;

import com.mongodb.MongoClient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@RequiredArgsConstructor
@Profile({"local", "test"})
@Import(EmbeddedMongoAutoConfiguration.class)
public class LocalRunConfig {

    @NonNull
    private MongoClient mongoClient;

    @NonNull
    private ResourceLoader resourceLoader;

    @PostConstruct
    private void initMongo() {
        fillMongo("data-sets/accounts.json", "accounts");
        fillMongo("data-sets/cards.json", "cards");
        fillMongo("data-sets/poas.json", "poas");
    }

    private void fillMongo(String fileName, String collectionName) {
        final var resource = resourceLoader.getResource(String.format("classpath:%s", fileName));
        final var entities = asString(resource).lines().map(Document::parse).collect(Collectors.toList());
        final var db = mongoClient.getDatabase("rabobank");
        db.createCollection(collectionName);
        db.getCollection(collectionName).insertMany(entities);
    }

    private static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
