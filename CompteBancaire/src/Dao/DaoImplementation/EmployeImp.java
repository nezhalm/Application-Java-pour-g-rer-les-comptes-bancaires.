package Dao.DaoImplementation;
import Dao.EmployeDao;
import Model.Employe;
import java.util.List;
import java.util.Optional;

public class EmployeImp implements EmployeDao {

    @Override
    public Optional<Employe> ajouter(Employe employe) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> supprimer(Employe employe) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<Employe> chercher(String var) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<List<Employe>> afficheList() throws Exception {
        return Optional.empty();
    }
}
