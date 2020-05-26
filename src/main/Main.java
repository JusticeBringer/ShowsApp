package main;// Project nr. 9: ticket reservation in a show (show, seat, client)

import model.event.Show;
import model.individual.Client;
import model.individual.Host;
import model.structure.Theatre;
import service.CSVService;
import service.ShowService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// class used to make requests
public class Main {
    private static List<Client> clients;
    private static List<Host> hosts;

    public static void main(String[] args) throws FileNotFoundException {
        csvCalls();
        serviceCalls();


    }

    private static void serviceCalls() throws FileNotFoundException {
        ShowService showService = new ShowService();
        CSVService csvService = new CSVService();

        csvService.writeInAuditFile("Showing all shows to the user.");
        showService.showAllShows();

        clients = csvService.getClients();

        double beforePurchase = clients.get(0).getMoney();
        boolean b = showService.buyTicket(clients.get(0));
        csvService.writeInAuditFile("Client with id 0 is buying a ticket");
        if (!b){
            System.out.println("No tickets available or user does not have funds");
        }
        else{
            System.out.print(clients.get(0).getFirstName() + " " + clients.get(0).getFamilyName() + " "
                    + "bought succesfuly a ticket. ");
            System.out.println("Client money was " + beforePurchase + " and now is " + clients.get(0).getMoney());
        }
        beforePurchase = clients.get(1).getMoney();
        b = showService.buyTicket(clients.get(1));
        csvService.writeInAuditFile("Client with id 1 is buying a ticket");
        if (!b){
            System.out.println("No tickets available or user does not have funds");
        }
        else{
            System.out.print(clients.get(1).getFirstName() + " " + clients.get(1).getFamilyName() + " "
                    + "bought succesfuly a ticket. ");
            System.out.println("Client money was " + beforePurchase + " and now is " + clients.get(1).getMoney());
        }
        beforePurchase = clients.get(2).getMoney();
        b = showService.buyTicket(clients.get(2));
        csvService.writeInAuditFile("Client with id 2 is buying a ticket");
        if (!b){
            System.out.println("No tickets available or user does not have funds");
        }
        else{
            System.out.print(clients.get(2).getFirstName() + " " + clients.get(2).getFamilyName() + " "
                    + "bought succesfuly a ticket. ");
            System.out.println("Client money was " + beforePurchase + " and now is " + clients.get(2).getMoney());
        }

        csvService.writeInAuditFile("Showing people attending to events");

        for (Client client : clients) {
            Map<String, Boolean> mp = client.getShowsAttend();
            for (Map.Entry<String, Boolean> entry : mp.entrySet()) {
                if (entry.getValue()) System.out.println(client.getFirstName()
                                                        + " " + client.getFamilyName()
                                                        + " attends to" + entry.getValue() + "");
            }
        }

        beforePurchase = hosts.get(0).getMoney();
        b = showService.hostEvent(hosts.get(0));
        csvService.writeInAuditFile("Host with id 0 is trying to host an event");
        if (!b){
                System.out.println("Event already hosted or host does not have funds");
        }
        else{
            System.out.print(hosts.get(0).getFirstName() + " " + hosts.get(0).getFamilyName() + " "
                    + "hosted succesfuly a show. ");
            System.out.println("Host money was " + beforePurchase + " and now is " + hosts.get(0).getMoney());
        }

        beforePurchase = clients.get(0).getMoney();
        b = showService.cancelTicket(clients.get(0));
        csvService.writeInAuditFile("Client with id 0 is trying to cancel ticket");
        if(!b){
            System.out.println("Ticket may be too old. Refund unsuccesful");
        }
        else {
            System.out.print(clients.get(0).getFirstName() + " " + clients.get(0).getFamilyName() + " "
                    + "refunded succesfully a ticket. ");

            System.out.println("Client money was " + beforePurchase + " and now is " + clients.get(0).getMoney());
        }

        beforePurchase = hosts.get(0).getMoney();
        List<Double> clMoney = new ArrayList<>();
        for (Client c : clients){
            clMoney.add(c.getMoney());
        }
        b = showService.cancelShow(hosts.get(0));
        csvService.writeInAuditFile("Host with id 0 is trying to cancel an event");
        if(!b){
            System.out.println("Event may be too old. Cancellation unsuccesful");
        }
        else {
            System.out.print(hosts.get(0).getFirstName() + " " + hosts.get(0).getFamilyName() + " "
                    + "cancelled succesfuly a show. ");
            System.out.println("Host money was " + beforePurchase + " and now is " + hosts.get(0).getMoney());

            System.out.println("Clients money before cancelling the show: ");
            clMoney.forEach(System.out::println);

            System.out.println("Clients money after cancelling the show: ");
            clients.forEach(c -> System.out.println(c.getMoney()));
        }

    }

    private static void csvCalls() throws FileNotFoundException {
        CSVService csvService = new CSVService();

        clients = csvService.getClients();
        csvService.writeInAuditFile("Extracting clients");
        System.out.println("Username clients: ");
        clients.forEach(c -> System.out.println(c.getUsername()));

        hosts = csvService.getHosts();
        csvService.writeInAuditFile("Extracting hosts");
        System.out.println("\nUsername hosts: ");
        hosts.forEach(h -> System.out.println(h.getUsername()));

        List<Show> shows = csvService.getShows();
        csvService.writeInAuditFile("Extracting shows");
        System.out.println("\nShows name: ");
        shows.forEach(s -> System.out.println(s.getTicket().getShowName()));

        List<Theatre> theatres = csvService.getTheatres();
        csvService.writeInAuditFile("Extracting theatres");
        System.out.println("\nTheatres name: ");
        theatres.forEach(t -> System.out.println(t.getName()));
    }
}
