package repositories;

import managers.DBConnectionManager;
import model.individual.Client;
import model.individual.Host;
import model.individual.Person;

import java.sql.*;

public class UserRepository {

    public void addUser(Person user) {
        String sql = "INSERT INTO users VALUES (NULL, ?, ?)";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client findUserByUsernameClient(String username) {
        String sql = "SELECT * FROM clients_data WHERE username = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, username);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            if (set.next()) {
                int id = set.getInt("idclients_data");
                String u = set.getString("username");
                String p = set.getString("password");

                System.out.println("here");
                return new Client(id, u, p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public Host findUserByUsernameHost(String username) {
        String sql = "SELECT * FROM hosts_data WHERE username = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, username);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            if (set.next()) {
                int id = set.getInt("idhosts_data");
                String u = set.getString("username");
                String p = set.getString("password");

                return new Host(id, u, p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}