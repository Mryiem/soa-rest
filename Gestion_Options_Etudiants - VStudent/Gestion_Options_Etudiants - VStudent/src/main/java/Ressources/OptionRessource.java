package Ressources;

import entities.Option;
import metiers.OptionBusiness;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("options")
public class OptionRessource {

    OptionBusiness optionBusiness = new OptionBusiness();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOption(Option option) {

        boolean added = optionBusiness.addOption(option);

        if (added) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Option> getAllOptions(
            @QueryParam("domaine") String domaine) {

        if (domaine != null) {
            return optionBusiness.getOptionsByDomaine(domaine);
        }

        return optionBusiness.getListeOptions();
    }
    @DELETE
    @Path("/{code}")
    public Response deleteOption(@PathParam("code") int code) {

        boolean deleted = optionBusiness.deleteOption(code);

        if (deleted) {
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @PUT
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOption(
            @PathParam("code") int code, Option option) {

        boolean updated = optionBusiness.updateOption(code, option);

        if (updated) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOptionByCode(@PathParam("code") int code) {

        Option option = optionBusiness.getOptionByCode(code);

        if (option != null) {
            return Response.ok(option).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}