package Dao.DaoImplementation;

import ConnexionBaseDonnes.Connexion;
import Dao.GlobaleDao;
import Model.Employe;
import Model.Operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OperationImp implements GlobaleDao<Operation> {

    @Override
    public Optional<Operation> ajouter(Operation operation) throws Exception {

        return null;
    }

    @Override
    public Optional<Integer> supprimer(Operation operation) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<Operation> chercher(String var) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<List<Operation>> afficheList() throws Exception {
        return Optional.empty();
    }

    public Optional<Employe> employeExiste(String matricule) throws Exception {
        String sql = "SELECT * FROM employee WHERE code = ?";
        Connection connection = Connexion.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, matricule);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String code = resultSet.getString("code");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                java.sql.Date birthDateSql = resultSet.getDate("birth_date");
                LocalDate birthDate = birthDateSql.toLocalDate();
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                java.sql.Date recruitedAtSql = resultSet.getDate("recruited_at");
                LocalDate recruitedAt = recruitedAtSql.toLocalDate();
                Employe employe = new Employe(code, firstName, lastName, birthDate, phoneNumber, email, recruitedAt);
                return Optional.of(employe);
            } else {
                return Optional.empty();
            }
    }

}
