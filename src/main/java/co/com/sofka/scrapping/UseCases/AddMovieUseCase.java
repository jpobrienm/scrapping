package co.com.sofka.scrapping.UseCases;

import co.com.sofka.scrapping.Domain.Catalog.Catalog;
import co.com.sofka.scrapping.Domain.Catalog.Commands.AddMovieCommand;
import co.com.sofka.scrapping.Domain.Generics.DomainEvent;
import co.com.sofka.scrapping.Infrastructure.Repository.MongoEventStoreRepository;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class AddMovieUseCase implements Function<AddMovieCommand, List<DomainEvent>> {

    private final MongoEventStoreRepository mongoEventStoreRepository;

    public AddMovieUseCase(MongoEventStoreRepository mongoEventStoreRepository){
        this.mongoEventStoreRepository = mongoEventStoreRepository;
    }

    @Override
    public List<DomainEvent> apply(AddMovieCommand addMovieCommand){
        var events = mongoEventStoreRepository.getEventsBy("movie", addMovieCommand.getMovieId());
        var catalog = Catalog.from(addMovieCommand.getCatalogId(), events);
        catalog.addMovie(addMovieCommand.getMovieId(), addMovieCommand.getName(), addMovieCommand.getUrl());
        return catalog.getUncommittedChanges();
    }

}
