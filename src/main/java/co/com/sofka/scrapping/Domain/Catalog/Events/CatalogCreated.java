package co.com.sofka.scrapping.Domain.Catalog.Events;

import co.com.sofka.scrapping.Domain.Generics.DomainEvent;

public class CatalogCreated extends DomainEvent {

    private final String id;
    private final String name;

    public CatalogCreated(String id, String name){
        super("co.com.sofka.scrapping.catalogcreated");
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
