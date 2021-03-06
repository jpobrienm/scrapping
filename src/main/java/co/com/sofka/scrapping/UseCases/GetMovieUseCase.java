package co.com.sofka.scrapping.UseCases;

import co.com.sofka.scrapping.Domain.Catalog.Catalog;
import co.com.sofka.scrapping.Domain.Catalog.Commands.GetMoviesCommand;
import co.com.sofka.scrapping.Domain.Generics.DomainEvent;
import co.com.sofka.scrapping.Infrastructure.Repository.MongoEventStoreRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@Dependent
public class GetMovieUseCase implements Function<GetMoviesCommand, List<DomainEvent>> {

    private final MongoEventStoreRepository mongoEventStoreRepository;
    private final String baseUrl = "https://pelisplus.so/estrenos";
    Document document = Jsoup.connect(baseUrl).get();

    public GetMovieUseCase(MongoEventStoreRepository mongoEventStoreRepository) throws IOException {
        this.mongoEventStoreRepository = mongoEventStoreRepository;
    }

    @Override
    public List<DomainEvent> apply(GetMoviesCommand getMovieCommand){
        var events = mongoEventStoreRepository.getEventsBy("movie", getMovieCommand.getMovieId());
        var catalog = Catalog.from(getMovieCommand.getCatalogId(), events);

        for(Element element: document.select(".items-peliculas .item-pelicula a")){
            String movieUrl = element.attr("href");
            try {
                final Document movie = Jsoup.connect("https://pelisplus.so" + movieUrl).get();
                String name = movie.select(".info-content h1").text();
                String url = movie.select(".player.player-normal ul:nth-of-type(2)  li:nth-of-type(1)")
                        .attr("data-video");

                catalog.addMovie(getMovieCommand.getMovieId(), name, url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return catalog.getUncommittedChanges();
    }

}
