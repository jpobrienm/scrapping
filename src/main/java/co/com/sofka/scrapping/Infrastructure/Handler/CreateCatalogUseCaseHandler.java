package co.com.sofka.scrapping.Infrastructure.Handler;

import co.com.sofka.scrapping.Domain.Catalog.Commands.CreateCatalogCommand;
import co.com.sofka.scrapping.Infrastructure.Generic.UseCaseHandle;
import co.com.sofka.scrapping.UseCases.CreateCatalogUseCase;
import io.quarkus.vertx.ConsumeEvent;

public class CreateCatalogUseCaseHandler extends UseCaseHandle {

    private final CreateCatalogUseCase createCatalogUseCase;

    public CreateCatalogUseCaseHandler(CreateCatalogUseCase createCatalogUseCase){
        this.createCatalogUseCase = createCatalogUseCase;
    }

    @ConsumeEvent(value="co.com.sofka.scrapping.catalogcreated")
    void consumeBlocking(CreateCatalogCommand createCatalogCommand){
        var events = createCatalogUseCase.apply(createCatalogCommand);
        saveCatalog(createCatalogCommand.getCatalogId(), events);
    }
}
