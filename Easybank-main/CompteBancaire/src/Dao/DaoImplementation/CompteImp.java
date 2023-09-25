package Dao.DaoImplementation;

import ConnexionBaseDonnes.Connexion;
import Dao.GlobaleDao;
import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class CompteImp implements GlobaleDao<Compte> {
    @Override
    public Optional<Compte> ajouter(Compte compte) throws Exception {

        return Optional.of(compte);

    }
    public Optional<Compte> ajouterCourant(CompteCourant compte) throws Exception {
        Connection connexion = Connexion.getInstance().getConnection();
        String sql = "INSERT INTO current_account (number, balance, created_at, overdraft, employee_code, client_code) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        preparedStatement.setString(1, compte.getNumero());
        preparedStatement.setDouble(2, compte.getSolde());
        preparedStatement.setDate(3, java.sql.Date.valueOf(compte.getDateCreation()));
        preparedStatement.setDouble(4,compte.getDecouvert() );
        preparedStatement.setString(5, compte.getEmploye().getMatricule());
        preparedStatement.setString(6, compte.getClient().getCode());
        preparedStatement.executeUpdate();
        return Optional.of(compte);

    }
    public Optional<Compte> ajouterEpargne(CompteEpargne compte) throws Exception {
        Connection connexion = Connexion.getInstance().getConnection();
        String sql = "INSERT INTO saving_account (number, balance, created_at, interest_rate, employee_code, client_code) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        preparedStatement.setString(1, compte.getNumero());
        preparedStatement.setDouble(2, compte.getSolde());
        preparedStatement.setDate(3, java.sql.Date.valueOf(compte.getDateCreation()));
        preparedStatement.setDouble(4, compte.getTauxInterer());
        preparedStatement.setString(5, compte.getEmploye().getMatricule());
        preparedStatement.setString(6, compte.getClient().getCode());
        preparedStatement.executeUpdate();
        return Optional.of(compte);
    }

    @Override
    public Optional<Integer> supprimer(Compte compte) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<Compte> chercher(String var) throws Exception {
        return Optional.empty();
    }

    public Optional<Compte> chercherParClient(Client client) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<List<Compte>> afficheList() throws Exception {
        return Optional.empty();
    }

    public Optional<List<CompteCourant>> afficheListCourant() throws Exception {
            List<CompteCourant> compteCourants = new ArrayList<>();
            Connection connection = Connexion.getInstance().getConnection();
            String query = "SELECT * FROM current_account ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Etat etat = Etat.valueOf(resultSet.getString("etat"));
                String number = resultSet.getString("number");
                Double balance = Double.valueOf(resultSet.getString("balance"));
                LocalDate created_at = LocalDate.parse(resultSet.getString("created_at"));
                int overdraft = Integer.parseInt(resultSet.getString("overdraft"));
                String employee_code = resultSet.getString("employee_code");
                String client_code = resultSet.getString("client_code");
                EmployeImp employe = new EmployeImp();
                ClientImp client = new ClientImp();
                Optional<Employe> emp = employe.chercher(employee_code);
                Optional<Client> cli = client.chercher(client_code);

                compteCourants.add(new CompteCourant(balance,number,created_at,etat,emp.get(),cli.get(),overdraft));
            }
            return Optional.ofNullable(compteCourants);

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
