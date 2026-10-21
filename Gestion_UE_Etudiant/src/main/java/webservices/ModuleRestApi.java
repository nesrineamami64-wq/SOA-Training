package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/modules")
public class ModuleRestApi {

    static ModuleBusiness helper = new ModuleBusiness();
    static UniteEnseignementBusiness ueHelper = new UniteEnseignementBusiness();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addModule(Module m) {
        if (helper.addModule(m)) {
            return Response.status(201).entity(m).build();
        }
        return Response.status(400)
                .entity("Ajout impossible : UE inexistante, UE manquante ou matricule déjà utilisé")
                .build();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeModules() {
        return Response.status(200).entity(helper.getAllModules()).build();
    }

    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(@PathParam("matricule") String matricule) {
        Module m = helper.getModuleByMatricule(matricule);
        if (m == null) {
            return Response.status(404).entity("Module introuvable").build();
        }
        return Response.status(200).entity(m).build();
    }

    @PUT
    @Path("/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateModule(@PathParam("matricule") String matricule, Module m) {
        Module old = helper.getModuleByMatricule(matricule);
        if (old == null) {
            return Response.status(404).entity("Module introuvable").build();
        }
        if (m.getUniteEnseignement() == null) {          // on garde l'UE actuelle
            m.setUniteEnseignement(old.getUniteEnseignement());
        }
        helper.updateModule(matricule, m);
        return Response.status(200).entity(m).build();
    }

    @DELETE
    @Path("/{matricule}")
    public Response deleteModule(@PathParam("matricule") String matricule) {
        if (helper.deleteModule(matricule)) {
            return Response.status(200).entity("Module supprimé").build();
        }
        return Response.status(404).entity("Module introuvable").build();
    }

    @GET
    @Path("/ue/{codeUE}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(@PathParam("codeUE") int codeUE) {
        UniteEnseignement ue = ueHelper.getUEByCode(codeUE);
        if (ue == null) {
            return Response.status(404).entity("UE introuvable").build();
        }
        return Response.status(200).entity(helper.getModulesByUE(ue)).build();
    }
}