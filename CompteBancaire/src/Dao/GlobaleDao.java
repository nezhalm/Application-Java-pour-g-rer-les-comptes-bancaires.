package Dao;
import java.util.List;
import java.util.Optional;

public interface GlobaleDao<T> {

        Optional<T> ajouter(T t) throws Exception;
        Optional<Integer>  supprimer(T t) throws Exception;
        Optional<T> chercher(String var) throws Exception;
        Optional<List<T>> afficheList() throws Exception;
}

