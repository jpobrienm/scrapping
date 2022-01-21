package co.com.sofka.scrapping.Infrastructure.Materializer;

import co.com.sofka.scrapping.Domain.Catalog.Events.CatalogCreated;
import com.mongodb.client.MongoClient;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class CatalogHandlerMaterializer {

    private final MongoClient mongoClient;

    public CatalogHandlerMaterializer(MongoClient mongoClient){
        this.mongoClient = mongoClient;
    }

    @ConsumeEvent(value="co.com.sofka.scrapping.catalogcreated", blocking = true)
    void consumeCatalogCreated(CatalogCreated catalogCreated){
        Map<String, Object> document = new HashMap<>();
        document.put("_id", catalogCreated.getAggregateId());
        document.put("nombre", catalogCreated.getName());

        mongoClient.getDatabase("queries")
                .getCollection("catalog")
                .insertOne(new Document(document));
    }
}
