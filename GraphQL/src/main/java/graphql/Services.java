package graphql;

import business.ModuleBusiness;
import business.UniteEnseignementBusiness;

public final class Services {

    public static final UniteEnseignementBusiness UE = new UniteEnseignementBusiness();
    public static final ModuleBusiness MODULE = new ModuleBusiness(UE);

    private Services() {
    }
}