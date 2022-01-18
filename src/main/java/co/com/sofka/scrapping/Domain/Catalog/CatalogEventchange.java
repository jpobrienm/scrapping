package co.com.sofka.scrapping.Domain.Catalog;

import co.com.sofka.scrapping.Domain.Catalog.Catalog;
import co.com.sofka.scrapping.Domain.Catalog.Entities.Movie;
import co.com.sofka.scrapping.Domain.Catalog.Events.CatalogCreated;
import co.com.sofka.scrapping.Domain.Catalog.Events.MovieAdded;
import co.com.sofka.scrapping.Domain.Generics.EventChange;

import java.util.HashMap;

public class CatalogEventchange implements EventChange {

    public CatalogEventchange(Catalog catalog){
        listener((CatalogCreated event) ->{
            catalog.id = event.getId();
            catalog.name = event.getName();
            catalog.movies = new HashMap<>();
        });

        listener((MovieAdded event) ->{
            var movie = new Movie(event.getId(), event.getName(), event.getUrl());
            catalog.movies.put(event.getName(), movie);
        });
    }

}
