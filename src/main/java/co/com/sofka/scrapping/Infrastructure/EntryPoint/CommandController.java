package co.com.sofka.scrapping.Infrastructure.EntryPoint;

import co.com.sofka.scrapping.Domain.Catalog.Commands.AddMovieCommand;
import co.com.sofka.scrapping.Domain.Catalog.Commands.CreateCatalogCommand;
import io.vertx.core.eventbus.EventBus;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/movies")
public class CommandController {
    private final EventBus bus;

    public CommandController(EventBus bus) {
        this.bus = bus;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createCatalog")
    public Response executor (CreateCatalogCommand command){
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addMovie")
    public Response executor (AddMovieCommand command){
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

}
