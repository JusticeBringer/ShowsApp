package main;// Project nr. 9: ticket reservation in a show (show, seat, client)

import model.event.Show;
import model.individual.Client;
import model.individual.Host;
import model.structure.Theatre;
import service.CSVService;
import service.ShowService;

import java.io.FileNotFoundException;
import java.util.List;

// class used to make requests
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ShowService showService = new ShowService();

//        showService.showAllLists();
//        showService.buyTicket();
//        showService.hostEvent();

        CSVService csvService = new CSVService();

        List<Client> clients = csvService.getClients();
        System.out.println("Username clients: ");
        clients.forEach(c -> System.out.println(c.getUsername()));

        List<Host> hosts = csvService.getHosts();
        System.out.println("Username hosts: ");
        hosts.forEach(h -> System.out.println(h.getUsername()));

        List<Show> shows = csvService.getShows();
        System.out.println("Shows name: ");
        shows.forEach(s -> System.out.println(s.getTicket().getShowName()));

        List<Theatre> theatres = csvService.getTheatres();
        System.out.println("Theatres name: ");
        theatres.forEach(t -> System.out.println(t.getName()));


    }
}
