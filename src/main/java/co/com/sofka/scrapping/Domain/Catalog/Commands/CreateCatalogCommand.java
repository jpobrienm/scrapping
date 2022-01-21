package co.com.sofka.scrapping.Domain.Catalog.Commands;

import co.com.sofka.scrapping.Domain.Generics.Command;

public class CreateCatalogCommand extends Command {

    private String catalogId;
    private String name;

    public CreateCatalogCommand(){

    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
