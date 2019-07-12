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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
    static void teardown() {
        mongodProcess.stop();
        mongodExecutable.stop();
    }

    @Test
    void testPersistence() {

        Document websiteDocument1 = new Document("site", "https://coder-coder.com/how-to-make-simple-website-html/").append("shown", false).append("date found", LocalDateTime.now());
        Document websiteDocument2 = new Document("site", "https://nl.wikipedia.org/wiki/Website").append("shown", false).append("date found", LocalDateTime.now());
        List<Document> documents = new ArrayList<>();
        documents.add(websiteDocument1);
        documents.add(websiteDocument2);
        Document document = new Document("keyword", "website")
                .append("websites", documents);
        mongoCollection.insertOne(document);
        Document check = mongoCollection.find().first();
        if (check != null) {
            System.out.println(check.toJson());
        } else {
            fail("Document is null");
        }
        assertEquals(document.get("keyword"), "website");
        assertEquals(1, mongoCollection.countDocuments());
    }
}
