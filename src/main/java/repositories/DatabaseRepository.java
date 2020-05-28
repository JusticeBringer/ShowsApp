package repositories;

import managers.DBConnectionManager;
import model.event.Seat;
import model.event.Show;
import model.event.Ticket;
import model.individual.Client;
import model.individual.Host;
import model.structure.Theatre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class DatabaseRepository {
    private String dbFile;
    private static Integer writerAUD = 0;

    public DatabaseRepository(){
        this.dbFile = "";
    }

    public List<Client> databaseClients(){
        List<Client> clients = new ArrayList<>();

        dbFile = "clients_data";
        String sql_qr = "SELECT * FROM " + dbFile;

        try {
            Connection con = DBConnectionManager.getInstance().createConnection();
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(sql_qr);
            // in RS avem randurile din tabela

            while (rs.next()) {
                int id = rs.getInt("idclients_data");
                String u = rs.getString("username");
                String p = rs.getString("password");
                String fN = rs.getString("firstName");
                String famN = rs.getString("familyName");
                String em = rs.getString("email");
                int age = rs.getInt("age");
                double money = rs.getDouble("money");

                Client c = new Client(id, u, p, fN, famN, em, age, money);
                clients.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }
    public List<Host> databaseHosts(){
        List<Host> hosts = new ArrayList<>();

        dbFile = "hosts_data";
        String sql_qr = "SELECT * FROM " + dbFile;

        try {
            Connection con = DBConnectionManager.getInstance().createConnection();
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(sql_qr);
            // in RS avem randurile din tabela

            while (rs.next()) {
                int id = rs.getInt("idhosts_data");
                String u = rs.getString("username");
                String p = rs.getString("password");
                String fN = rs.getString("firstName");
                String famN = rs.getString("familyName");
                String em = rs.getString("email");
                int age = rs.getInt("age");
                double money = rs.getDouble("money");

                Host h = new Host(id, u, p, fN, famN, em, age, money);
                hosts.add(h);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hosts;
    }
    public List<Ticket> databaseTickets(){
        List<Ticket> tickets = new ArrayList<>();

        dbFile = "tickets";
        String sql_qr = "SELECT * FROM " + dbFile;

        try {
            Connection con = DBConnectionManager.getInstance().createConnection();
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(sql_qr);
            // in RS avem randurile din tabela

            while (rs.next()) {
                int id = rs.getInt("idtickets");
                int price = rs.getInt("price");
                int year = rs.getInt("year");
                int month = rs.getInt("month");
                int day = rs.getInt("day");
                String showName = rs.getString("showName");
                String showLocation = rs.getString("showLocation");

                Ticket t = new Ticket(id, price, year, month, day, showName, showLocation);
                tickets.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }
    public List<Show> databaseShows(){
        List<Ticket> tickets = databaseTickets();
        List<Host> hosts = databaseHosts();
        List<Show> shows = new ArrayList<>();

        dbFile = "shows";
        String sql_qr = "SELECT * FROM " + dbFile;

        try {
            Connection con = DBConnectionManager.getInstance().createConnection();
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(sql_qr);
            // in RS avem randurile din tabela

            while (rs.next()) {
                int id = rs.getInt("idshows");
                int seats = rs.getInt("seats");

                Seat seat = new Seat(seats);
                Show show;

                if (id % 2 == 0) {
                    show = new Show(id, seat, tickets.get(id - 1));
                    show.setHasHost(false);
                }
                else{
                    show = new Show(id, seat, tickets.get(id - 1), hosts.get(id-1));
                }
                shows.add(show);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shows;
    }
    public List<Theatre> databaseTheatres(){
        List<Show> shows = databaseShows();
        List<Theatre> theatres = new ArrayList<>();

        dbFile = "theatres";
        String sql_qr = "SELECT * FROM " + dbFile;

        try {
            Connection con = DBConnectionManager.getInstance().createConnection();
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(sql_qr);
            // in RS avem randurile din tabela

            while (rs.next()) {
                int id = rs.getInt("idtheatres");
                double surface = rs.getDouble("surface");
                int height = rs.getInt("height");
                String architecture = rs.getString("architecture");
                String name = rs.getString("name");
                String theatreLocation = rs.getString("theatreLocation");

                Theatre theatre = new Theatre(id, surface, height, architecture, name, shows.get(id - 1), theatreLocation);

                theatres.add(theatre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return theatres;
    }

    public int getTheatreSeats(int idOfShow){
        List<Theatre> theatres = databaseTheatres();
        System.out.println(theatres.get(idOfShow).getShow().getSeat().getNrSeats());

        return theatres.get(idOfShow).getShow().getSeat().getNrSeats();
    }

}
