package Dao.DaoImplementation;

import ConnexionBaseDonnes.Connexion;
import Dao.CompteEpargnesDao;
import Dao.GlobaleDao;
import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompteEpargneImp implements CompteEpargnesDao {
    private static final Connection connection = Connexion.getInstance().getConnection();
@Override
    public Optional<CompteEpargne> ajouter(CompteEpargne compteEpargne) throws Exception {

        String sql = "INSERT INTO saving_account (number, balance, created_at, interest_rate, employee_code, client_code) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, compteEpargne.getNumero());
        preparedStatement.setDouble(2, compteEpargne.getSolde());
        preparedStatement.setDate(3, java.sql.Date.valueOf(compteEpargne.getDateCreation()));
        preparedStatement.setDouble(4, compteEpargne.getTauxInterer());
        preparedStatement.setString(5, compteEpargne.getEmploye().getMatricule());
        preparedStatement.setString(6, compteEpargne.getClient().getCode());
        preparedStatement.executeUpdate();
        return Optional.of(compteEpargne);
    }




    @Override
    public Optional<Integer> supprimer(CompteEpargne compteEpargne) throws Exception {
        String sql = "DELETE FROM saving_account WHERE number = ?";
        Connection connexion = Connexion.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setString(1, compteEpargne.getNumero());
            preparedStatement.executeUpdate();
            return Optional.empty();
        }
    }

    @Override
    public Optional<CompteEpargne> chercher(String var) throws Exception {
        Optional<List<CompteEpargne>> employes = afficheList();
        List<CompteEpargne> employeList = employes.get();
        return employeList.stream()
                .filter(employe -> employe.getNumero().equals(var))
                .findFirst();

    }


    public Optional<List<CompteEpargne>> afficheList() throws Exception {
        List<CompteEpargne> compteEpargnes = new ArrayList<>();
        String query = "SELECT * FROM saving_account ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String accountStatusValue = resultSet.getString("account_status");
            Etat etat = null;
            switch (accountStatusValue) {
                case "Active":
                    etat = Etat.ACTIVE;
                    break;
                case "Frozen":
                    etat = Etat.FROZEN;
                    break;
                case "Closed":
                    etat = Etat.CLOSED;
                    break;
            }
            String number = resultSet.getString("number");
            Double balance = Double.valueOf(resultSet.getString("balance"));
            LocalDate created_at = LocalDate.parse(resultSet.getString("created_at"));
            Double interset_rate = (double) Integer.parseInt(resultSet.getString("interest_rate"));
            String employee_code = resultSet.getString("employee_code");
            String client_code = resultSet.getString("client_code");
            EmployeImp employe = new EmployeImp();
            ClientImp client = new ClientImp();
            Optional<Employe> emp = employe.chercher(employee_code);
            Optional<Client> cli = client.chercher(client_code);
            compteEpargnes.add(new CompteEpargne(balance,number,created_at,etat,emp.get(),cli.get(),interset_rate));
        }
        return Optional.ofNullable(compteEpargnes);
    }
    public Optional<CompteEpargne> changerStatus(CompteEpargne compteEpargne, Etat etat) throws Exception {
        String sql ="UPDATE saving_account SET account_status = ?::accountstatus WHERE number = ?";
        Connection connexion = Connexion.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setString(1, etat.toString());
            preparedStatement.setString(2, compteEpargne.getNumero());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.empty();
            } else {
                return Optional.ofNullable(compteEpargne);
            }
        }
    }

    public static Optional<CompteEpargne> retrait(CompteEpargne compteEpargne, Employe employe , Double montant, String typeOperation){
        if (montant < 0) {
            throw new IllegalArgumentException("Le montant du versement doit être positif");
        }
        if (compteEpargne.getSolde() < montant) {
            throw new IllegalArgumentException("Fonds insuffisants pour effectuer le retrait");
        }
        try {
            String sql = "UPDATE saving_account SET balance = balance - ? WHERE number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, montant);
            preparedStatement.setString(2, compteEpargne.getNumero()); // Remplacez par le nom réel de la colonne d'identification
            int rowsAffected = preparedStatement.executeUpdate();

            // Si la mise à jour a réussi, créez une opération
            if (rowsAffected > 0) {
                OperationImp operationImp = new OperationImp();
                operationImp.ajouter(compteEpargne,employe,montant, Integer.parseInt(typeOperation));
            } else {
                throw new SQLException("Échec de la mise à jour du solde du compte courant");
            }
        } catch ( SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la mise à jour du solde du compte courant", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(compteEpargne);
    }

    public static Optional<CompteEpargne> versement(CompteEpargne compteEpargne, Employe employe , Double montant, String typeOperation){
        if (montant < 0) {
            throw new IllegalArgumentException("Le montant du versement doit être positif");
        }

        try {
            String sql = "UPDATE saving_account SET balance = balance + ? WHERE number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, montant);
            preparedStatement.setString(2, compteEpargne.getNumero()); // Remplacez par le nom réel de la colonne d'identification
            int rowsAffected = preparedStatement.executeUpdate();

            // Si la mise à jour a réussi, créez une opération
            if (rowsAffected > 0) {
                OperationImp operationImp = new OperationImp();
                operationImp.ajouter(compteEpargne,employe,montant, Integer.parseInt(typeOperation));
            } else {
                throw new SQLException("Échec de la mise à jour du solde du compte courant");
            }
        } catch ( SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la mise à jour du solde du compte courant", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(compteEpargne);
    }
}
