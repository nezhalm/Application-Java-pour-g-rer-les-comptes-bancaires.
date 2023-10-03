package Dao.DaoImplementation;

import ConnexionBaseDonnes.Connexion;
import Dao.MissionDao;
import Model.Client;
import Model.Employe;
import Model.Mission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MissionImp implements MissionDao {

    @Override
    public Optional<Mission> ajouter(Mission mission) throws Exception {
        Connection connexion = Connexion.getInstance().getConnection();
        String sql = "INSERT INTO mission (code, name, description) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        preparedStatement.setString(1, mission.getCode());
        preparedStatement.setString(2, mission.getNom());
        preparedStatement.setString(3, mission.getDescription());
        preparedStatement.executeUpdate();
        return Optional.ofNullable(mission);
    }


    @Override
    public Optional<Integer> supprimer(Mission mission) throws Exception {
        String sql = "DELETE FROM mission WHERE code = ?";
        Connection connexion = Connexion.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setString(1, mission.getCode());
            preparedStatement.executeUpdate();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Mission> chercher(String var) throws Exception {
        Optional<List<Mission>> missions = afficheList();
        List<Mission> missionList = missions.get();
        return missionList.stream()
                .filter(client -> client.getCode().equals(var))
                .findFirst();
    }

    @Override
    public Optional<List<Mission>> afficheList()  throws SQLException {
        List<Mission> missions = new ArrayList<>();
        Connection connection = Connexion.getInstance().getConnection();
        String query = "SELECT * FROM mission";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String code = resultSet.getString("code");
            String nom = resultSet.getString("name");
            String description = resultSet.getString("description");

            missions.add(new Mission(code, nom, description));
        }
        return Optional.ofNullable(missions);
    }
}
