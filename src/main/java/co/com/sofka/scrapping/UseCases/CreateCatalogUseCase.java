package co.com.sofka.scrapping.UseCases;

import co.com.sofka.scrapping.Domain.Catalog.Catalog;
import co.com.sofka.scrapping.Domain.Catalog.Commands.CreateCatalogCommand;
import co.com.sofka.scrapping.Domain.Generics.DomainEvent;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class CreateCatalogUseCase implements Function<CreateCatalogCommand, List<DomainEvent>> {

    @Override
    public List<DomainEvent> apply(CreateCatalogCommand createCatalogCommand){
        var catalog = new Catalog(createCatalogCommand.getCatalogId(), createCatalogCommand.getName());
        return catalog.getUncommittedChanges();
    }
}
