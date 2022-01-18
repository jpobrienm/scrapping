package co.com.sofka.scrapping.Domain.Catalog.Events;

import co.com.sofka.scrapping.Domain.Generics.DomainEvent;

public class MovieAdded extends DomainEvent {

    private final String id;
    private String name;
    private String url;

    public MovieAdded(String id, String name, String url){
        super("co.com.sofka.scrapping.movieadded");
        this.id = id;
        this.name = name;
        this.url = url;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

}
