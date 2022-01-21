package co.com.sofka.scrapping.Infrastructure.Handler;

import co.com.sofka.scrapping.Domain.Catalog.Commands.GetMoviesCommand;
import co.com.sofka.scrapping.Infrastructure.Generic.UseCaseHandle;
import co.com.sofka.scrapping.UseCases.GetMovieUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetMoviesUseCaseHandler extends UseCaseHandle {

    private final GetMovieUseCase getMovieUseCase;

    public GetMoviesUseCaseHandler(GetMovieUseCase getMovieUseCase){
        this.getMovieUseCase = getMovieUseCase;
    }

    @ConsumeEvent(value="co.com.sofka.scrapping.movieget")
    void consumeBlocking(GetMoviesCommand getMovieCommand){
        var events = getMovieUseCase.apply(getMovieCommand);
        saveCatalog(getMovieCommand.getCatalogId(), events);
    }

}
