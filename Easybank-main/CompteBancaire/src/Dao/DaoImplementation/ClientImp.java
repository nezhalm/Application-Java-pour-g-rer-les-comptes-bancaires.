package Dao.DaoImplementation;
import ConnexionBaseDonnes.Connexion;
import Dao.GlobaleDao;
import Model.Client;
import Model.Employe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;

public class ClientImp implements GlobaleDao<Client> {


    @Override
    public Optional<Client> ajouter(Client client) throws Exception {
        return null;
    }

    @Override
    public Optional<Integer> supprimer(Client client) throws Exception {
        String sql = "DELETE FROM client WHERE code = ?";
        Connection connexion = Connexion.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setString(1, client.getCode());
            preparedStatement.executeUpdate();
        return Optional.empty();
        }
    }

    @Override
    public Optional<Client> chercher(String code) throws SQLException {
        Optional<List<Client>> clientsOptional = afficheList();
            List<Client> clients = clientsOptional.get();
            return clients.stream()
                    .filter(client -> client.getCode().equals(code))
                    .findFirst();
    }


    @Override
    public Optional<List<Client>> afficheList()  throws SQLException {
        List<Client> clients = new ArrayList<>();
        Connection connection = Connexion.getInstance().getConnection();
        String query = "SELECT * FROM client";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");
                Date birthDateSql = resultSet.getDate("birth_date");
                LocalDate birthDate = ((java.sql.Date) birthDateSql).toLocalDate();
                String address = resultSet.getString("address");
                clients.add(new Client(firstName, lastName, phoneNumber, birthDate,code, address));
                    }
        return Optional.ofNullable(clients);
    }
    public Optional<Client> ajouterClient(Client client, Optional<Employe> employe) throws Exception {
        Connection connexion = Connexion.getInstance().getConnection();
        String sql = "INSERT INTO client (code, first_name, last_name, birth_date, phone_number, address, creator_employe) VALUES (?, ?, ?, ?, ?, ?, ?)";
       PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, client.getCode());
            preparedStatement.setString(2, client.getNom());
            preparedStatement.setString(3, client.getPrenom());
            preparedStatement.setObject(4, client.getDateNaissance());
            preparedStatement.setString(5, client.getTelephone());
            preparedStatement.setString(6, client.getAdresse());
            preparedStatement.setObject(7, employe.get().getMatricule());
            preparedStatement.executeUpdate();
        return Optional.empty();    }
}
