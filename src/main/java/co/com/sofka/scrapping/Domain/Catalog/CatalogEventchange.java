package co.com.sofka.scrapping.Domain.Catalog;

import co.com.sofka.scrapping.Domain.Catalog.Entities.Movie;
import co.com.sofka.scrapping.Domain.Catalog.Events.CatalogCreated;
import co.com.sofka.scrapping.Domain.Catalog.Events.MovieAdded;
import co.com.sofka.scrapping.Domain.Catalog.Events.MoviesGotten;
import co.com.sofka.scrapping.Domain.Generics.EventChange;

import java.util.HashMap;

public class CatalogEventchange implements EventChange {

    public CatalogEventchange(Catalog catalog){

        listener((CatalogCreated event) ->{
            catalog.id = event.getCatalogId();
            catalog.name = event.getName();
            catalog.movies = new HashMap<>();
        });

        listener((MovieAdded event) ->{
            var movie = new Movie(event.getCatalogId(), event.getName(), event.getUrl());
            catalog.movies.put(event.getName(), movie);
        });

        listener((MoviesGotten event) ->{
            var movie = new Movie(event.getMovieId(), event.getName(), event.getUrl());
            catalog.movies.put(event.getName(), movie);
        });
    }

}
