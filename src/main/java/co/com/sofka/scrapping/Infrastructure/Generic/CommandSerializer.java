package co.com.sofka.scrapping.Infrastructure.Generic;

import co.com.sofka.scrapping.Domain.Generics.Command;

import java.lang.reflect.Type;

public final class CommandSerializer extends AbstractSerializer {

    private static CommandSerializer commandSerializer;

    private CommandSerializer() {
        super();
    }
    
    public static synchronized CommandSerializer instance() {
        if (CommandSerializer.commandSerializer == null) {
            CommandSerializer.commandSerializer = new CommandSerializer();
        }
        return CommandSerializer.commandSerializer;
    }

    
    public <T extends Command> T deserialize(String aSerialization, final Class<?> aType) {
        return gson.fromJson(aSerialization, (Type) aType);
    }

    public String serialize(Command object) {
        return gson.toJson(object);
    }

}