package co.com.sofka.scrapping.Infrastructure.Materializer;

import co.com.sofka.scrapping.Domain.Catalog.Events.CatalogCreated;
import co.com.sofka.scrapping.Domain.Catalog.Events.MovieAdded;
import co.com.sofka.scrapping.Domain.Catalog.Events.MoviesGotten;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
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

    @ConsumeEvent(value="co.com.sofka.scrapping.movieAdded", blocking = true)
    void consumeMovieAdded(MovieAdded movieAdded){
        Map<String, Object> document = new HashMap<>();
        document.put("_id", movieAdded.getAggregateId());
        document.put("nombre", movieAdded.getName());
        document.put("url", movieAdded.getUrl());

        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("catalog")
                .updateOne(Filters.eq("_id", movieAdded.getAggregateId()), basicDBObject);
    }

    @ConsumeEvent(value="co.com.sofka.scrapping.moviegotten", blocking = true)
    void consumeMoviesGotten(MoviesGotten moviesGotten){
        Map<String, Object> document = new HashMap<>();
        document.put("_id", moviesGotten.getAggregateId());
        document.put("nombre", moviesGotten.getName());
        document.put("url", moviesGotten.getUrl());

        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("catalog")
                .updateOne(Filters.eq("_id", moviesGotten.getAggregateId()), basicDBObject);
    }

}
