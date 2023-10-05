package Dao.DaoImplementation;

import Dao.ClientDao;
import Dao.GlobaleDao;
import Model.Compte;

import java.util.List;
import java.util.Optional;

public class CompteImp implements GlobaleDao<Compte> {

    @Override
    public Optional<Compte> ajouter(Compte compte) throws Exception {
        return Optional.empty();
    }
//hfhgfhhh
    @Override
    public Optional<Integer> supprimer(Compte compte) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<Compte> chercher(String var) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<List<Compte>> afficheList() throws Exception {
        return Optional.empty();
    }
}
