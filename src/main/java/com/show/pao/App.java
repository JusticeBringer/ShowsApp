package com.show.pao; // Project nr. 9: ticket reservation in a show (show, seat, client)

import loginArea.LoginFrame;
import model.event.Show;
import model.individual.Client;
import model.individual.Host;
import model.structure.Theatre;
import service.AuditService;
import service.DatabaseService;
import service.ShowService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// class used to make requests
public class App {
    private static List<Client> dbClients;
    private static List<Host> dbHosts;
    private static List<Show> dbShows;
    private static List<Theatre> dbTheatres;

    private static DatabaseService databaseService;
    private static AuditService auditService;

    public static void main(String[] args) throws FileNotFoundException {
        // initializing services
        initServices();

        // load all the database
        databaseCalls();

        // app demo in console
        serviceCalls();

        //starting GUI
        loginAreaCall();

    }
    
    private static void loginAreaCall(){
        LoginFrame loginFrame = new LoginFrame();

    }

    private static void initServices(){
        auditService = new AuditService();
        databaseService = new DatabaseService();
    }


    private static void databaseCalls(){
        loadClients();
        loadHosts();
        loadShows();
        loadTheatres();
    }


    private static void loadClients(){
        auditService.writeInAuditFile("Extracting clients", Thread.currentThread().getName());
        dbClients = databaseService.getDBClients();
        dbClients.forEach(c -> System.out.println(c.getUsername()));
    }

    private static void loadHosts(){
        auditService.writeInAuditFile("Extracting hosts", Thread.currentThread().getName());
        dbHosts = databaseService.getDBHosts();
        dbHosts.forEach(c -> System.out.println(c.getUsername()));
    }

    private static void loadShows(){
        auditService.writeInAuditFile("Extracting shows", Thread.currentThread().getName());
        dbShows = databaseService.getDBShows();
        dbShows.forEach(c -> System.out.println(c.getTicket().getShowName()));
    }

    private static void loadTheatres(){
        auditService.writeInAuditFile("Extracting theatres", Thread.currentThread().getName());
        dbTheatres = databaseService.getDBTheatres();
        dbTheatres.forEach(c -> System.out.println(c.getName()));
    }

    private static void serviceCalls() throws FileNotFoundException {
        ShowService showService = new ShowService();

        auditService.writeInAuditFile("Showing all shows to the user.", Thread.currentThread().getName());
        showService.showAllShows();

        dbClients = databaseService.getDBClients();

        double beforePurchase = dbClients.get(0).getMoney();
        boolean b = showService.buyTicket(dbClients.get(0));
        auditService.writeInAuditFile("Client with id 0 is buying a ticket", Thread.currentThread().getName());
        if (!b){
            System.out.println("No tickets available or user does not have funds");
        }
        else{
            System.out.print(dbClients.get(0).getFirstName() + " " + dbClients.get(0).getFamilyName() + " "
                    + "bought succesfuly a ticket. ");
            System.out.println("Client money was " + beforePurchase + " and now is " + dbClients.get(0).getMoney());
        }
        beforePurchase = dbClients.get(1).getMoney();
        b = showService.buyTicket(dbClients.get(1));
        auditService.writeInAuditFile("Client with id 1 is buying a ticket", Thread.currentThread().getName());
        if (!b){
            System.out.println("No tickets available or user does not have funds");
        }
        else{
            System.out.print(dbClients.get(1).getFirstName() + " " + dbClients.get(1).getFamilyName() + " "
                    + "bought succesfuly a ticket. ");
            System.out.println("Client money was " + beforePurchase + " and now is " + dbClients.get(1).getMoney());
        }
        beforePurchase = dbClients.get(2).getMoney();
        b = showService.buyTicket(dbClients.get(2));
        auditService.writeInAuditFile("Client with id 2 is buying a ticket", Thread.currentThread().getName());
        if (!b){
            System.out.println("No tickets available or user does not have funds");
        }
        else{
            System.out.print(dbClients.get(2).getFirstName() + " " + dbClients.get(2).getFamilyName() + " "
                    + "bought succesfuly a ticket. ");
            System.out.println("Client money was " + beforePurchase + " and now is " + dbClients.get(2).getMoney());
        }

        auditService.writeInAuditFile("Showing people attending to events", Thread.currentThread().getName());

        for (Client client : dbClients) {
            Map<String, Boolean> mp = client.getShowsAttend();
            for (Map.Entry<String, Boolean> entry : mp.entrySet()) {
                if (entry.getValue()) System.out.println(client.getFirstName()
                        + " " + client.getFamilyName()
                        + " attends to" + entry.getValue() + "");
            }
        }

        beforePurchase = dbHosts.get(0).getMoney();
        b = showService.hostEvent(dbHosts.get(0));
        auditService.writeInAuditFile("Host with id 0 is trying to host an event", Thread.currentThread().getName());
        if (!b){
            System.out.println("Event already hosted or host does not have funds");
        }
        else{
            System.out.print(dbHosts.get(0).getFirstName() + " " + dbHosts.get(0).getFamilyName() + " "
                    + "hosted succesfuly a show. ");
            System.out.println("Host money was " + beforePurchase + " and now is " + dbHosts.get(0).getMoney());
        }

        beforePurchase = dbClients.get(0).getMoney();
        b = showService.cancelTicket(dbClients.get(0));
        auditService.writeInAuditFile("Client with id 0 is trying to cancel ticket", Thread.currentThread().getName());
        if(!b){
            System.out.println("Ticket may be too old. Refund unsuccesful");
        }
        else {
            System.out.print(dbClients.get(0).getFirstName() + " " + dbClients.get(0).getFamilyName() + " "
                    + "refunded succesfully a ticket. ");

            System.out.println("Client money was " + beforePurchase + " and now is " + dbClients.get(0).getMoney());
        }

        beforePurchase = dbHosts.get(0).getMoney();
        List<Double> clMoney = new ArrayList<>();
        for (Client c : dbClients){
            clMoney.add(c.getMoney());
        }
        b = showService.cancelShow(dbHosts.get(0));
        auditService.writeInAuditFile("Host with id 0 is trying to cancel an event", Thread.currentThread().getName());
        if(!b){
            System.out.println("Event may be too old. Cancellation unsuccesful");
        }
        else {
            System.out.print(dbHosts.get(0).getFirstName() + " " + dbHosts.get(0).getFamilyName() + " "
                    + "cancelled succesfuly a show. ");
            System.out.println("Host money was " + beforePurchase + " and now is " + dbHosts.get(0).getMoney());

            System.out.println("Clients money before cancelling the show: ");
            clMoney.forEach(System.out::println);

            System.out.println("Clients money after cancelling the show: ");
            dbClients.forEach(c -> System.out.println(c.getMoney()));
        }

    }
}
