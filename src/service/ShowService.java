package service;

import model.event.Show;
import model.individual.Client;
import model.individual.Host;
import model.structure.Theatre;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class ShowService {

    private CSVService csvService;

    public ShowService() {
        csvService = new CSVService();
    }

    public void showAllShows() throws FileNotFoundException {
        List<Theatre> theatres = csvService.getTheatres();
        System.out.println("\n\nShows list: ");

        for (var t:
             theatres) {
            System.out.print(t.getShow().getTicket().getShowName() + " will be at " + t.getShow().getTicket().getShowLocation()
                                + " and hosted at " + t.getName() + " . ");
            System.out.print(t.getShow().getTicket().getPrice() + "$ is this show ticket price. "
                                + "Show Date: " + t.getShow().getTicket().getDay()
                                + " " + t.getShow().getTicket().getMonth() + " " + t.getShow().getTicket().getYear()
                                + ". " + t.getShow().getHost().getFirstName() + " " + t.getShow().getHost().getFamilyName()
                                + " is organising the show. Contact him at this email address: "
                                + t.getShow().getHost().getEmail());

            int seatsAvailable = 0;
            Map<Integer, Boolean> seats = t.getShow().getSeat().getAllSeats();
            for (Map.Entry<Integer, Boolean> entry : seats.entrySet()) {
                if( !entry.getValue()) seatsAvailable ++;
            }

            System.out.println(". There are "+ seatsAvailable + " available tickets.");
        }
    }

    public boolean buyTicket(Client client) throws FileNotFoundException {
        List<Theatre> theatres = csvService.getTheatres();

        int seatsAvailable = 0;
        Map<Integer, Boolean> seats = theatres.get(0).getShow().getSeat().getAllSeats();
        for (Map.Entry<Integer, Boolean> entry : seats.entrySet()) {
            if( !entry.getValue()) seatsAvailable ++;
        }

        if (seatsAvailable == 0){
            return false;
        }

        if(theatres.get(0).getShow().getTicket().getPrice() > client.getMoney()){
            return false;
        }

        client.setMoney(client.getMoney() - theatres.get(0).getShow().getTicket().getPrice());
        Map<String, Boolean> shClient = client.getShowsAttend();

        // we set client attending to event
        shClient.put(theatres.get(0).getShow().getTicket().getShowName(), true);
        client.setShowsAttend(shClient);

        return true;
    }

    public boolean hostEvent(Host host) throws FileNotFoundException {
        List<Theatre> theatres = csvService.getTheatres();

        int seatsAvailable = 0;
        Map<Integer, Boolean> seats = theatres.get(0).getShow().getSeat().getAllSeats();
        for (Map.Entry<Integer, Boolean> entry : seats.entrySet()) {
            if( !entry.getValue()) seatsAvailable ++;
        }

        if (seatsAvailable == 0){
            return false;
        }

        if(theatres.get(1).getShow().getTicket().getPrice() > host.getMoney()){
            return false;
        }

        //event already hosted
        if (theatres.get(1).getShow().getHasHost()) return false;

        Map<String, String> hsShows = host.getShowsHost();

        // we set the host of an event
        hsShows.put(theatres.get(1).getShow().getTicket().getShowName(), host.getUsername());
        theatres.get(1).getShow().setHasHost(true);
        host.setShowsHost(hsShows);

        host.setMoney(host.getMoney() - theatres.get(1).getShow().getTicket().getPrice());

        return true;
    }

}
