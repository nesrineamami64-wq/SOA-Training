package graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import entities.Module;
import entities.UniteEnseignement;

import java.util.List;

public class QueryResolver implements GraphQLQueryResolver {

    // ===== Modules =====
    public List<Module> modules() {
        return Services.MODULE.getAllModules();
    }

    public List<Module> modulesByType(Module.TypeModule type) {
        return Services.MODULE.getModulesByType(type);
    }

    public Module moduleByMatricule(String matricule) {
        return Services.MODULE.getModuleByMatricule(matricule);
    }

    // ===== Unites d enseignement =====
    public UniteEnseignement uniteEnseignement(int code) {
        return Services.UE.getUEByCode(code);
    }

    public List<UniteEnseignement> uniteEnseignementsByDomaine(String domaine) {
        return Services.UE.getUEByDomaine(domaine);
    }

    public List<UniteEnseignement> uniteEnseignementsBySemestre(int semestre) {
        return Services.UE.getUEBySemestre(semestre);
    }
}