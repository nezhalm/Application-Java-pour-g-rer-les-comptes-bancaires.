package Dao.DaoImplementation;
import ConnexionBaseDonnes.Connexion;
import Dao.GlobaleDao;
import Model.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;
public class ClientImp implements GlobaleDao<Client> {

//dfddssfdfgfdgsdsvdgggghghghjgjjjhjghhhh
    @Override
    public Optional<Client> ajouter(Client client) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> supprimer(Client client) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<Client> chercher(String code) throws SQLException {
        Optional<List<Client>> clientsOptional = afficheList();
        if (clientsOptional.isPresent()) {
            List<Client> clients = clientsOptional.get();
            return clients.stream()
                    .filter(client -> client.getCode().equals(code))
                    .findFirst();
        } else {
            return Optional.empty();
        }
    }


    @Override
    public Optional<List<Client>> afficheList()  {
        List<Client> clients = new ArrayList<>();
        Connection connection = Connexion.getInstance().getConnection();
        String query = "SELECT * FROM client";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Date birthDate = resultSet.getDate("birth_date");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                clients.add(new Client(firstName, lastName, phoneNumber, birthDate,code, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(clients);
    }
}
