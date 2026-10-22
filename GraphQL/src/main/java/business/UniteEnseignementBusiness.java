package business;

import entities.UniteEnseignement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UniteEnseignementBusiness {

    private final List<UniteEnseignement> unites = new ArrayList<>();

    public UniteEnseignementBusiness() {
        // Donnees initiales pour pouvoir tester tout de suite
        unites.add(new UniteEnseignement(1, "Informatique", "Dr Ben Ali", 6, 1));
        unites.add(new UniteEnseignement(2, "Mathematiques", "Dr Trabelsi", 4, 1));
        unites.add(new UniteEnseignement(3, "Informatique", "Dr Gharbi", 5, 2));
    }

    public synchronized boolean addUE(UniteEnseignement ue) {
        if (getUEByCode(ue.getCode()) != null) {
            return false; // code deja utilise
        }
        return unites.add(ue);
    }

    public synchronized UniteEnseignement getUEByCode(int code) {
        return unites.stream().filter(u -> u.getCode() == code).findFirst().orElse(null);
    }

    public synchronized List<UniteEnseignement> getUEByDomaine(String domaine) {
        return unites.stream()
                .filter(u -> u.getDomaine() != null && u.getDomaine().equalsIgnoreCase(domaine))
                .collect(Collectors.toList());
    }

    public synchronized List<UniteEnseignement> getUEBySemestre(int semestre) {
        return unites.stream().filter(u -> u.getSemestre() == semestre).collect(Collectors.toList());
    }

    public synchronized UniteEnseignement updateUE(UniteEnseignement nouvelle) {
        UniteEnseignement existante = getUEByCode(nouvelle.getCode());
        if (existante == null) {
            return null;
        }
        existante.setDomaine(nouvelle.getDomaine());
        existante.setResponsable(nouvelle.getResponsable());
        existante.setCredits(nouvelle.getCredits());
        existante.setSemestre(nouvelle.getSemestre());
        return existante;
    }

    public synchronized boolean deleteUE(int code) {
        return unites.removeIf(u -> u.getCode() == code);
    }
}