package main;// Project nr. 9: ticket reservation in a show (show, seat, client)

import service.ShowService;

// class used to make requests
public class Main {
    public static void main(String[] args) {
        ShowService showService = new ShowService();

        showService.showAllLists();
        showService.buyTicket();
        showService.hostEvent();
    }
}
