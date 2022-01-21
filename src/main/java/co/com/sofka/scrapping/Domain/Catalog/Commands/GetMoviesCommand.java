package co.com.sofka.scrapping.Domain.Catalog.Commands;

import co.com.sofka.scrapping.Domain.Generics.Command;

public class GetMoviesCommand extends Command {

    private String catalogId;
    private String movieId;
    private String name;
    private String url;

    public GetMoviesCommand() {
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
