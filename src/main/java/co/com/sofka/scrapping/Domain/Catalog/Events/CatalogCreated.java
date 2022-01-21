package co.com.sofka.scrapping.Domain.Catalog.Events;

import co.com.sofka.scrapping.Domain.Generics.DomainEvent;

public class CatalogCreated extends DomainEvent {

    private final String catalogId;
    private final String name;

    public CatalogCreated(String id, String name){
        super("co.com.sofka.scrapping.catalogcreated");
        this.catalogId = id;
        this.name = name;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public String getName() {
        return name;
    }
}
