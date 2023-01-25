package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.GuideDTO;
import dtos.TripDTO;
import entities.TripHasGuide;
import facades.AdminFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("admin")
public class AdminResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final AdminFacade FACADE =  AdminFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @POST
    @Path("createtrip")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes ({MediaType.APPLICATION_JSON})
    //@RolesAllowed("admin")
    public String createTrip(String input) {
        TripDTO tripDTO = GSON.fromJson(input, TripDTO.class);
        TripDTO newTrip = FACADE.createTrip(tripDTO);
        return GSON.toJson(newTrip);
    }

    @POST
    @Path("createguide")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes ({MediaType.APPLICATION_JSON})
    //@RolesAllowed("admin")
    public String createGuide(String input) {
        GuideDTO guideDTO = GSON.fromJson(input, GuideDTO.class);
        GuideDTO newGuide = FACADE.createGuide(guideDTO);
        return GSON.toJson(newGuide);
    }

/*    @PUT
    @Path("edittripguide/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    //@RolesAllowed("admin")
    public Response updateTripGuide(@PathParam("id") int id, String a) {
        TripHasGuide tripDTO = GSON.fromJson(a, TripDTO.class);
        TripDTO.setId();
        ProjectHoursDTO result = FACADE.updateProjectHours(projectHoursDTO);
        return Response.ok().entity(GSON.toJson(result)).build();
    }*/


    @DELETE
    @Path("deletetrip/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    //@RolesAllowed("admin")
    public Response deleteTrip(@PathParam("id") int id) {
        FACADE.deleteTrip(id);
        return Response.ok().entity(GSON.toJson(id)).build();
    }
}