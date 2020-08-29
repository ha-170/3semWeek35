package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author hassanainali
 */
@Path("animals_db")
public class AnimalFromDB {

    @Context
    private UriInfo context;
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        }   finally {
                em.close();
        }
    }   
    
    @Path("/animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findAnimalById(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try{
            Animal a = em.find(Animal.class,id);
            return new Gson().toJson(a);
        }   finally {
            em.close();
        }
    }      
   
    @Path("/animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findAnimalByType(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a where a.type=:type", Animal.class);
            List<Animal> lis = query.setParameter("type", type).getResultList();
            return new Gson().toJson(lis);
        }   catch (NoResultException e){
            return null;
        }
        finally {
            em.close();
        }
    }
    
    @Path("/random_animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String randomAnimal(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a ORDER BY FUNCTION('RAND')", Animal.class);
            List<Animal> lis = query.setMaxResults(1).getResultList();
            return new Gson().toJson(lis);
        }   catch (NoResultException e){
            return null;
        }
        finally {
            em.close();
        }
    }
}