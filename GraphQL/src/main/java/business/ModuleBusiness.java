package business;

import entities.Module;
import entities.UniteEnseignement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleBusiness {

    private final List<Module> modules = new ArrayList<>();

    public ModuleBusiness(UniteEnseignementBusiness ueBusiness) {
        // Donnees initiales pour pouvoir tester tout de suite
        UniteEnseignement ue1 = ueBusiness.getUEByCode(1);
        UniteEnseignement ue2 = ueBusiness.getUEByCode(2);
        modules.add(new Module("M101", "Algorithmique", 3, 42, Module.TypeModule.PROFESSIONNEL, ue1));
        modules.add(new Module("M102", "Anglais", 2, 21, Module.TypeModule.TRANSVERSAL, ue2));
        modules.add(new Module("M103", "Methodologie de recherche", 1, 21, Module.TypeModule.RECHERCHE, ue1));
    }

    public synchronized List<Module> getAllModules() {
        return new ArrayList<>(modules);
    }

    public synchronized List<Module> getModulesByType(Module.TypeModule type) {
        return modules.stream().filter(m -> m.getType() == type).collect(Collectors.toList());
    }

    public synchronized Module getModuleByMatricule(String matricule) {
        return modules.stream().filter(m -> m.getMatricule().equals(matricule)).findFirst().orElse(null);
    }

    public synchronized boolean addModule(Module module) {
        if (getModuleByMatricule(module.getMatricule()) != null) {
            return false; // matricule deja utilise
        }
        return modules.add(module);
    }

    public synchronized Module updateModule(Module nouveau) {
        Module existant = getModuleByMatricule(nouveau.getMatricule());
        if (existant == null) {
            return null;
        }
        existant.setNom(nouveau.getNom());
        existant.setCoefficient(nouveau.getCoefficient());
        existant.setVolumeHoraire(nouveau.getVolumeHoraire());
        existant.setType(nouveau.getType());
        existant.setUniteEnseignement(nouveau.getUniteEnseignement());
        return existant;
    }

    public synchronized boolean deleteModule(String matricule) {
        return modules.removeIf(m -> m.getMatricule().equals(matricule));
    }
}