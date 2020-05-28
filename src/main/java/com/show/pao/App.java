package com.show.pao; // Project nr. 9: ticket reservation in a show (show, seat, client)

import GUI.clientArea.ClientFrame;
import GUI.hostArea.HostFrame;
import GUI.loginArea.LoginFrame;
import model.event.Show;
import model.individual.Client;
import model.individual.Host;
import model.structure.Theatre;
import service.AuditService;
import service.DatabaseService;
import service.ShowService;

import javax.swing.*;
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

        //starting GUI
        GUICalls();

    }

    private static void GUICalls(){
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setTitle("Shows App");
        while (!loginFrame.isCanGoNext()) {
            loginFrame = loginFrame;
        }

        // we close last panel
        loginFrame.setVisible(false);

        JRadioButton clOrHost = loginFrame.getClientOrHost();

        if (clOrHost.isSelected()){
            ClientFrame clientFrame = new ClientFrame();
            clientFrame.setTitle("Shows App - Client");

            // we put banner of welcome with the name of logged in client
            Client lgC = loginFrame.getLoggedInClient();
            clientFrame.setUserMoney(lgC.getMoney());
            clientFrame.setUsername(lgC.getFirstName() + " " + lgC.getFamilyName());

            //we list available shows
            ShowService showService = new ShowService();
            auditService.writeInAuditFile("Showing all shows to the user.", Thread.currentThread().getName());

            // set nr Shows
            clientFrame.setHowManyShows(dbTheatres.size());

            int nr = -1;
            for (Theatre t: dbTheatres){
                nr ++;

                String details = showService.showSendDetails(nr);
                clientFrame.setMoreShows(details);

            }

            // implement buying ticket
            // send the client to client frame
            clientFrame.setClient(lgC);


            //implement refunding ticket


            // we open the client panel
            clientFrame.showThePanel();
        }
        else{
            HostFrame hostFrame = new HostFrame();
            hostFrame.setTitle("Shows App - Host");

            Host lgH = loginFrame.getLoggedInHost();
            hostFrame.setUserMoney(lgH.getMoney());
            hostFrame.setUsername(lgH.getFirstName() + " " + lgH.getFamilyName());

            // we open the host panel
            hostFrame.showThePanel();
        }


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
//
}
