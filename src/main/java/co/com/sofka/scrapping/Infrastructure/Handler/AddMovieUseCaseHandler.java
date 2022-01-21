package co.com.sofka.scrapping.Infrastructure.Handler;

import co.com.sofka.scrapping.Domain.Catalog.Commands.AddMovieCommand;
import co.com.sofka.scrapping.Infrastructure.Generic.UseCaseHandle;
import co.com.sofka.scrapping.UseCases.AddMovieUseCase;
import io.quarkus.vertx.ConsumeEvent;

public class AddMovieUseCaseHandler extends UseCaseHandle {

    private final AddMovieUseCase addMovieUseCase;

    public AddMovieUseCaseHandler(AddMovieUseCase addMovieUseCase){
        this.addMovieUseCase = addMovieUseCase;
    }

    @ConsumeEvent(value="co.com.sofka.scrapping.movieadded")
    void consumeBlocking(AddMovieCommand addMovieCommand){
        var events = addMovieUseCase.apply(addMovieCommand);
        saveCatalog(addMovieCommand.getCatalogId(), events);
    }

}
