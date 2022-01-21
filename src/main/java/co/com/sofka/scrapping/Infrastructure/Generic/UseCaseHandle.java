package co.com.sofka.scrapping.Infrastructure.Generic;

import co.com.sofka.scrapping.Domain.Generics.DomainEvent;
import co.com.sofka.scrapping.Domain.Generics.EventStoreRepository;
import co.com.sofka.scrapping.Domain.Generics.StoredEvent;
import co.com.sofka.scrapping.Infrastructure.Message.BusService;
import co.com.sofka.scrapping.Infrastructure.Repository.MongoEventStoreRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public abstract class UseCaseHandle {
    @Inject
    private MongoEventStoreRepository repository;

    @Inject
    private BusService busService;;

    public void saveCatalog(String catalogId, List<DomainEvent> events) {
        events.stream().map(event -> {
            String eventBody = EventSerializer.instance().serialize(event);
            return new StoredEvent(event.getClass().getTypeName(), new Date(), eventBody);
        }).forEach(storedEvent -> repository.saveEvent("program", catalogId, storedEvent));

        events.forEach(busService::send);
    }
}
