package Dao.DaoImplementation;
import ConnexionBaseDonnes.Connexion;
import Dao.EmployeDao;
import Model.Client;
import Model.Employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EmployeImp implements EmployeDao {

    @Override
    public Optional<Employe> ajouter(Employe employe) throws Exception {
        Connection connexion = Connexion.getInstance().getConnection();
        String sql = "INSERT INTO employee (code, first_name, last_name, birth_date, phone_number, email, recruited_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setString(1, employe.getMatricule());
            preparedStatement.setString(2, employe.getNom());
            preparedStatement.setString(3, employe.getPrenom());
            preparedStatement.setObject(4, employe.getDateNaissance());
            preparedStatement.setString(5, employe.getTelephone());
            preparedStatement.setString(6, employe.getEmail());
            preparedStatement.setObject(7, employe.getDateRecrutement());

            int lignesModifiees = preparedStatement.executeUpdate();
            if (lignesModifiees > 0) {
                return Optional.of(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return Optional.empty();

    }
    @Override
    public Optional<Integer> supprimer(Employe employe) throws Exception {
        String sql = "DELETE FROM employee WHERE code = ?";
        Connection connexion = Connexion.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setString(1, employe.getMatricule());
            preparedStatement.executeUpdate();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employe> chercher(String matricule) throws Exception {
        Optional<List<Employe>> employes = afficheList();
        List<Employe> employeList = employes.get();
        return employeList.stream()
                .filter(employe -> employe.getMatricule().equals(matricule))
                .findFirst();
    }

    @Override
    public Optional<List<Employe>> afficheList() throws Exception {
        List<Employe> employes = new ArrayList<>();
        Connection connection = Connexion.getInstance().getConnection();
        String query = "SELECT * FROM employee";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String code = resultSet.getString("code");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String phoneNumber = resultSet.getString("phone_number");
            Date birthDateSql = resultSet.getDate("birth_date");
            LocalDate birthDate = ((java.sql.Date) birthDateSql).toLocalDate();
            Date dateRecrutement = resultSet.getDate("recruited_at");
            LocalDate rec = ((java.sql.Date) dateRecrutement).toLocalDate();
            String  email = resultSet.getString("email");
            employes.add(new Employe(code, firstName, lastName, birthDate,phoneNumber, email,rec));
        }
        return Optional.ofNullable(employes);
    }



}
