package Dao.DaoImplementation;

import ConnexionBaseDonnes.Connexion;
import Dao.OperationDao;
import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class OperationImp implements OperationDao {


    @Override
    public Optional<Operation> ajouter(Operation operation) throws Exception {

        return null;
    }

    public Optional<CompteCourant> ajouter(CompteCourant compteCourant, Employe employe, Double montant,int type) throws Exception {
        Connection connexion = Connexion.getInstance().getConnection();
        String sql = "INSERT INTO operation (number, created_at, price,payment ,employee_code, account_number) VALUES (?, ?, ?,  CAST(? AS paymenttype), ?, ?)";
        String operationType = (type == 1) ? "Withdrawal" : (type == 2) ? "Deposit" : "Autre";

        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        preparedStatement.setString(1, genererCodeUnique(6));
        preparedStatement.setDate(2, java.sql.Date.valueOf(compteCourant.getDateCreation()));
        preparedStatement.setDouble(3, montant);
        preparedStatement.setString(4, operationType);
        preparedStatement.setString(5, employe.getMatricule());
        preparedStatement.setString(6, compteCourant.getNumero());
        preparedStatement.executeUpdate();
        return Optional.ofNullable(compteCourant);
    }

    public Optional<CompteEpargne> ajouter(CompteEpargne compteEpargne, Employe employe, Double montant, int type) throws Exception {
        Connection connexion = Connexion.getInstance().getConnection();
        String sql = "INSERT INTO operation (number, created_at, price,payment ,employee_code,account_number) VALUES (?, ?, ?,  CAST(? AS paymenttype), ?, ?)";
        String operationType = (type == 1) ? "Withdrawal" : (type == 2) ? "Deposit" : "Autre";

        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        preparedStatement.setString(1, genererCodeUnique(6));
        preparedStatement.setDate(2, java.sql.Date.valueOf(compteEpargne.getDateCreation()));
        preparedStatement.setDouble(3, montant);
        preparedStatement.setString(4, operationType);
        preparedStatement.setString(5, employe.getMatricule());
        preparedStatement.setString(6, compteEpargne.getNumero());
        preparedStatement.executeUpdate();
        return Optional.ofNullable(compteEpargne);
    }



    @Override
    public Optional<Integer> supprimer(Operation operation) throws Exception {
        String sql = "DELETE FROM operation WHERE number = ?";
        Connection connexion = Connexion.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setString(1, operation.getNumero());
            preparedStatement.executeUpdate();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Operation> chercher(String var) throws Exception {
        Optional<List<Operation>> operations = afficheList();
        List<Operation> operationList = operations.get();
        return operationList.stream()
                .filter(operation -> operation.getNumero().equals(var))
                .findFirst();
    }

    @Override
    public Optional<List<Operation>> afficheList() throws Exception {
        List<Operation> operations = new ArrayList<>();
        EmployeImp employes = new EmployeImp();
        CompteCourantImp compteCourantImp = new CompteCourantImp();

        try (Connection connection = Connexion.getInstance().getConnection()) {
            String query = "SELECT * FROM operation";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String number = resultSet.getString("number");
                LocalDate createdAt = resultSet.getDate("created_at").toLocalDate();
                int price = resultSet.getInt("price");
                String employeeCode = resultSet.getString("employee_code");
                String accountNumber = resultSet.getString("account_number");
                TypeOperation type = TypeOperation.valueOf(resultSet.getString("payment"));

                Employe employe = employes.chercher(employeeCode).orElse(null);
                CompteCourant compteCourant = compteCourantImp.chercher(accountNumber).orElse(null);

                Operation operation = new Operation(employe, compteCourant, number, createdAt, price,type);
                operations.add(operation);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'exception ici
        }

        return Optional.ofNullable(operations);
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


    public static String genererCodeUnique(int longueur) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < longueur; i++) {
            int chiffre = random.nextInt(10);
            code.append(chiffre);
        }
        return code.toString();
    }


}
