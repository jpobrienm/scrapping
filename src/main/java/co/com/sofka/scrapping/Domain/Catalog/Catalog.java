package co.com.sofka.scrapping.Domain.Catalog;

import co.com.sofka.scrapping.Domain.Catalog.Entities.Movie;
import co.com.sofka.scrapping.Domain.Catalog.Events.CatalogCreated;
import co.com.sofka.scrapping.Domain.Catalog.Events.MovieAdded;
import co.com.sofka.scrapping.Domain.Generics.AggregateRoot;
import co.com.sofka.scrapping.Domain.Generics.DomainEvent;

import java.util.List;
import java.util.Map;

public class Catalog extends AggregateRoot {

    protected String id;
    protected Map<String, Movie> movies;
    protected String name;

    private Catalog(String id){
        super(id);
        subscribe(new CatalogEventchange(this));
    }

    public Catalog(String id, String name){
        super(id);
        subscribe(new CatalogEventchange(this));
        appendChange(new CatalogCreated(id, name)).apply();
    }

    public static Catalog from(String id, List<DomainEvent> events){
        var catalog = new Catalog(id);
        events.forEach(catalog::applyEvent);
        return catalog;
    }

    public void addMovie(String movieId, String name, String url){
        appendChange(new MovieAdded(movieId, name, url)).apply();
    }

    public String name(){return name;}

    public Movie getMovieById(String movieId){return movies.get(movieId);}

}
