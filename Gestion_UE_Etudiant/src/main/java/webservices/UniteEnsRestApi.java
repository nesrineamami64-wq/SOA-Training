package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/ue")
public class UniteEnsRestApi {

    static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUE(UniteEnseignement ue) {
        if (helper.getUEByCode(ue.getCode()) != null) {
            return Response.status(409).entity("Code déjà existant").build();
        }
        if (helper.addUniteEnseignement(ue)) {
            return Response.status(201).entity(ue).build();
        }
        return Response.status(400).entity("Erreur lors de l'ajout").build();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeUE(@QueryParam("semestre") Integer semestre) {
        List<UniteEnseignement> res = (semestre == null)
                ? helper.getListeUE()
                : helper.getUEBySemestre(semestre);
        return Response.status(200).entity(res).build();
    }

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEbyCode(@PathParam("code") int code) {
        UniteEnseignement ue = helper.getUEByCode(code);
        if (ue == null) {
            return Response.status(404).entity("UE introuvable").build();
        }
        return Response.status(200).entity(ue).build();
    }

    @PUT
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUE(@PathParam("code") int code, UniteEnseignement ue) {
        if (helper.updateUniteEnseignement(code, ue)) {
            return Response.status(200).entity(ue).build();
        }
        return Response.status(404).entity("UE introuvable").build();
    }

    @DELETE
    @Path("/{code}")
    public Response deleteUE(@PathParam("code") int code) {
        if (helper.deleteUniteEnseignement(code)) {
            return Response.status(200).entity("UE supprimée").build();
        }
        return Response.status(404).entity("UE introuvable").build();
    }
}