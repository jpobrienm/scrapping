package co.com.sofka.scrapping.Domain.Catalog.Events;

import co.com.sofka.scrapping.Domain.Generics.DomainEvent;

public class MoviesGotten extends DomainEvent {

    private String catalogId;
    private String movieId;
    private String name;
    private String url;

    public MoviesGotten(String catalogId, String movieId, String name, String url) {
        super("co.com.sofka.scrapping.moviegotten");
        this.catalogId = catalogId;
        this.movieId = movieId;
        this.name = name;
        this.url = url;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
