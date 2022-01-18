package co.com.sofka.scrapping.Domain.Catalog.Entities;

import java.util.Objects;

public class Movie {

    private final String id;
    private String name;
    private String url;

    public Movie(String id, String name, String url){
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.id) && Objects.equals(name, movie.name) && Objects.equals(url, movie.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
