package co.com.sofka.scrapping.Infrastructure.Generic;


import co.com.sofka.scrapping.Domain.Generics.StoredEvent;

public final class StoredEventSerializer extends AbstractSerializer {

    private static StoredEventSerializer eventSerializer;

    private StoredEventSerializer() {
        super();
    }


    public static synchronized StoredEventSerializer instance() {
        if (StoredEventSerializer.eventSerializer == null) {
            StoredEventSerializer.eventSerializer = new StoredEventSerializer();
        }
        return StoredEventSerializer.eventSerializer;
    }


    public StoredEvent deserialize(String aSerialization, Class<StoredEvent> aType) {
        return gson.fromJson(aSerialization, aType);
    }


    public String serialize(StoredEvent object) {
        return gson.toJson(object);
    }

}