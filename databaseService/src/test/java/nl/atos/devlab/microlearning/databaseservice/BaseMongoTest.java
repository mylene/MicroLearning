package nl.atos.devlab.microlearning.databaseservice;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseMongoTest {

    private static final MongodStarter starter = MongodStarter.getDefaultInstance();

    private static MongodExecutable mongodExecutable;
    private static MongodProcess mongodProcess;

    private static MongoCollection<Document> mongoCollection;

    @BeforeAll
    static void setup() throws Exception {
        mongodExecutable = starter.prepare(new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net("localhost", 12345, Network.localhostIsIPv6()))
                .build());
        mongodProcess = mongodExecutable.start();

        MongoClient mongoClient = new MongoClient("localhost", 12345);

        // Creating DB
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        mongoCollection = mongoDatabase.getCollection("myTestCollection");
    }

    @AfterAll
    static void teardown(){
        mongodProcess.stop();
        mongodExecutable.stop();
    }

    @Test
    void testPersistence() {
        Document document = new Document("name", "Café Con Leche")
                .append("contact", new Document("phone", "228-555-0149")
                        .append("email", "cafeconleche@example.com")
                        .append("location", Arrays.asList(-73.92502, 40.8279556)))
                .append("stars", 3)
                .append("categories", Arrays.asList("Bakery", "Coffee", "Pastries"));

        mongoCollection.insertOne(document);
        assertEquals(document.get("name"), "Café Con Leche");
    }
}
