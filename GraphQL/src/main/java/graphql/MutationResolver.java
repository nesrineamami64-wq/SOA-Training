package graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import entities.Module;
import entities.UniteEnseignement;

public class MutationResolver implements GraphQLMutationResolver {

    // ===== Modules =====
    public boolean addModule(String matricule, String libelle, int coef,
                             int volumeHoraire, int codeUE, Module.TypeModule type) {
        UniteEnseignement ue = Services.UE.getUEByCode(codeUE);
        if (ue == null) {
            return false; // l unite d enseignement doit exister
        }
        return Services.MODULE.addModule(new Module(matricule, libelle, coef, volumeHoraire, type, ue));
    }

    public Module updateModule(String matricule, String libelle, int coef,
                               int volumeHoraire, Module.TypeModule type, int codeUE) {
        UniteEnseignement ue = Services.UE.getUEByCode(codeUE);
        if (ue == null) {
            return null;
        }
        return Services.MODULE.updateModule(new Module(matricule, libelle, coef, volumeHoraire, type, ue));
    }

    public String deleteModule(String matricule) {
        return Services.MODULE.deleteModule(matricule) ? "deleted successfully" : "module not found";
    }

    // ===== Unites d enseignement =====
    public boolean addUniteEnseignement(int code, String domaine, String responsable,
                                        int credits, int semestre) {
        return Services.UE.addUE(new UniteEnseignement(code, domaine, responsable, credits, semestre));
    }

    public UniteEnseignement updateUniteEnseignement(int code, String domaine, String responsable,
                                                     int credits, int semestre) {
        return Services.UE.updateUE(new UniteEnseignement(code, domaine, responsable, credits, semestre));
    }

    public String deleteUniteEnseignement(int code) {
        return Services.UE.deleteUE(code) ? "deleted successfully" : "UE not found";
    }
}