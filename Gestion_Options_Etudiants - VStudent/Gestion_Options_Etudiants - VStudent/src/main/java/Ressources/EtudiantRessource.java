package Ressources;

import entities.Etudiant;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import entities.Option;
import entities.EtudiantList;
import entities.Option;
@Path("etudiants")
public class EtudiantRessource {

    EtudiantBusiness etudiantBusiness = new EtudiantBusiness();
    OptionBusiness optionBusiness = new OptionBusiness();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEtudiant(Etudiant etudiant) {

        boolean added = etudiantBusiness.addEtudiant(etudiant);

        if (added) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etudiant> getAllEtudiants() {
        return etudiantBusiness.getAllEtudiants();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantById(@PathParam("id") String id) {

        Etudiant etudiant =
                etudiantBusiness.getEtudiantByIdentifiant(id);

        if (etudiant != null) {
            return Response.ok(etudiant).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteEtudiant(@PathParam("id") String id) {

        boolean deleted = etudiantBusiness.deleteEtudiant(id);

        if (deleted) {
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(
            @PathParam("id") String id, Etudiant etudiant) {

        boolean updated =
                etudiantBusiness.updateEtudiant(id, etudiant);

        if (updated) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Path("/option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtudiantsByOption(
            @QueryParam("codeOption") int codeOption) {

        Option option = optionBusiness.getOptionByCode(codeOption);

        if (option == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Etudiant> etudiants =
                etudiantBusiness.getEtudiantsByOption(option);

        return Response.ok(new EtudiantList(etudiants)).build();
    }



}
