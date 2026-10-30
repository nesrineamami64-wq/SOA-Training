package ressources;

import entities.UniteEnseignement;
import filtres.Secured;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("UE")
public class UERessources {

    public static UniteEnseignementBusiness uniteEnseignementMetier = new UniteEnseignementBusiness();

    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response addUniteEnseignement(UniteEnseignement ue) {
        if(uniteEnseignementMetier.addUniteEnseignement(ue))
            return Response.status(Response.Status.CREATED).build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnitesEnseignement(@QueryParam("semestre") Integer semestre) {
        List<UniteEnseignement> liste = new ArrayList<>();

        if(semestre != null) {
            liste = uniteEnseignementMetier.getUEBySemestre(semestre);
        } else {
            liste = uniteEnseignementMetier.getListeUE();
        }

        if(liste.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.OK).entity(liste).build();
    }

    @PUT
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUE(UniteEnseignement updatedUE, @PathParam("code") int code) {
        if(uniteEnseignementMetier.updateUniteEnseignement(code, updatedUE)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{code}")
    public Response deleteUniteEnseignement(@PathParam("code") int code) {
        if(uniteEnseignementMetier.deleteUniteEnseignement(code))
            return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUniteEnseignementById(@PathParam("code") int code) {
        UniteEnseignement ue = uniteEnseignementMetier.getUEByCode(code);
        if(ue != null)
            return Response.status(Response.Status.OK).entity(ue).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
