package repositories;

import managers.DBConnectionManager;
import model.individual.Person;
import service.AuditService;

import java.sql.*;

public class UserRepository {

    private AuditService auditService = new AuditService();

    public void addUser(Person user) {
        auditService.writeInAuditFile("Adding user to database...", Thread.currentThread().getName());

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

    public String findUserByUsernameClient(String username) {
        auditService.writeInAuditFile("Trying to find client by username", Thread.currentThread().getName());

        String sql_qr = "SELECT * FROM clients_data WHERE username = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql_qr);
               )

        {
            statement.setString(1, username);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            // in RS avem randurile din tabela

            if (set.next()) {
                int id = set.getInt("idclients_data");
                String u = set.getString("username");
                String p = set.getString("password");

                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String findUserByUsernameHost(String username) {
        auditService.writeInAuditFile("Trying to find host by username", Thread.currentThread().getName());

        String sql_qr = "SELECT * FROM hosts_data WHERE username = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql_qr);
        )

        {
            statement.setString(1, username);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            // in RS avem randurile din tabela

            if (set.next()) {
                int id = set.getInt("idhosts_data");
                String u = set.getString("username");
                String p = set.getString("password");

                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateClientMoney(int clientID, double newMoneyValue){
        auditService.writeInAuditFile("Updating a client money", Thread.currentThread().getName());

        String sql_qr = "UPDATE clients_data SET money = ? WHERE idclients_data = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql_qr);
        )

        {
            statement.setDouble(1, newMoneyValue);
            statement.setInt(2, clientID);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateHostMoney(int hostID, double newMoneyValue) {
        auditService.writeInAuditFile("Updating a host money", Thread.currentThread().getName());

        String sql_qr = "UPDATE hosts_data SET money = ? WHERE idhosts_data = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql_qr);
        )

        {
            statement.setDouble(1, newMoneyValue);
            statement.setInt(2, hostID);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSeatsAtShow(int getNrShowNrToBuy, int seatsNewNumber) {
        auditService.writeInAuditFile("Updating seat number at a show", Thread.currentThread().getName());

        String sql_qr = "UPDATE shows SET seats = ? WHERE idshows = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql_qr);
        )

        {
            statement.setDouble(1, seatsNewNumber);
            statement.setInt(2, getNrShowNrToBuy);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteShowFromGivenId(int getNrShowNrToCancel) {
        auditService.writeInAuditFile("Deleting a show from given id ...", Thread.currentThread().getName());

        String sql_qr = "DELETE FROM shows WHERE idshows = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql_qr);
        )

        {
            statement.setInt(1, getNrShowNrToCancel);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTicketFromGivenId(int getNrShowNrToCancel) {
        auditService.writeInAuditFile("Deleting a ticket from given id ...", Thread.currentThread().getName());

        String sql_qr = "DELETE FROM tickets WHERE idtickets = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql_qr);
        )

        {
            statement.setInt(1, getNrShowNrToCancel);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTheatreFromGivenId(int getNrShowNrToCancel) {
        auditService.writeInAuditFile("Deleting theatre from given id ...", Thread.currentThread().getName());

        String sql_qr = "DELETE FROM theatres WHERE idtheatres = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql_qr);
        )

        {
            statement.setInt(1, getNrShowNrToCancel);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}