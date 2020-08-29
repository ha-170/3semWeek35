package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.AnimalNoDB;

/**
 * REST Web Service
 *
 * @author hassanainali
 */

@Path("animals")
public class AnimalDemo {

    @Context
    private UriInfo context;
    
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of AnimalDemo
     */
    public AnimalDemo() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalDemo
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        //TODO return proper representation object
        return "Vuf… (Message from a dog)";
    }
    
    @GET
    @Path("/animal_list")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2(){
        return "[\"Dog\", \"Cat\", \"Mouse\", \"Bird\"]";
    }
    
    @GET
    @Path("/animal")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson3(){
        AnimalNoDB animal = new AnimalNoDB("Duck", "Quack");
        return new Gson().toJson(animal);
    }
}
