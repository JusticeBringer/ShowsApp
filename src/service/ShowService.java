package service;

import model.event.Show;
import model.individual.Client;
import model.individual.Host;
import model.structure.Theatre;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            if (t.getShow().getHasHost()){

                System.out.print(t.getShow().getTicket().getPrice() + "$ is this show ticket price. "
                        + "Show Date: " + t.getShow().getTicket().getDay()
                        + " " + t.getShow().getTicket().getMonth() + " " + t.getShow().getTicket().getYear()
                        + ". " + t.getShow().getHost().getFirstName() + " " + t.getShow().getHost().getFamilyName()
                        + " is organising the show. Contact him at this email address: "
                        + t.getShow().getHost().getEmail());
            }
            else{
                System.out.print(t.getShow().getTicket().getPrice() + "$ is this show ticket price. "
                        + "Show Date: " + t.getShow().getTicket().getDay()
                        + " " + t.getShow().getTicket().getMonth() + " " + t.getShow().getTicket().getYear());
            }

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

        //update seats list
        Map<Integer, Boolean> sts = theatres.get(0).getShow().getSeat().getAllSeats();
        for (Map.Entry<Integer, Boolean> ent : sts.entrySet()){
            //find first unnocupied seat and set to occupied
            if (!ent.getValue()){
                sts.put(ent.getKey(), true);
                break;
            }
        }

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

        if(theatres.get(0).getShow().getTicket().getPrice() > host.getMoney()){
            return false;
        }

        //event already hosted
        if (theatres.get(0).getShow().getHasHost()) return false;

        Map<String, String> hsShows = host.getShowsHost();

        // we set the host of an event
        hsShows.put(theatres.get(0).getShow().getTicket().getShowName(), host.getUsername());
        theatres.get(0).getShow().setHasHost(true);
        host.setShowsHost(hsShows);

        //update host price
        host.setMoney(host.getMoney() - theatres.get(0).getShow().getTicket().getPrice());
        return true;
    }

    public boolean cancelTicket(Client client) throws FileNotFoundException {
        List<Theatre> theatres = csvService.getTheatres();

        Map<String, Boolean> clientAttend = client.getShowsAttend();

        // if user is not attending any event
        if (clientAttend.size() == 0){
            return false;
        }

        for (Map.Entry<String, Boolean> entry : clientAttend.entrySet()) {
            for (Theatre theatre: theatres){
                if (entry.getKey().equals(theatre.getShow().getTicket().getShowName())
                   && entry.getValue()){
                    //we see if the show has been already hosted
                    Date date = new Date();

                    SimpleDateFormat ft =
                            new SimpleDateFormat ("yyyy MM dd");

                    String eventDate = "";
                    eventDate += theatre.getShow().getTicket().getYear() + " " + theatre.getShow().getTicket().getMonth()
                                + " " + theatre.getShow().getTicket().getDay();

                    // event already has been
                    if (ft.format(date).compareTo(eventDate) >= 0){
                        return false; //cant cancel ticket
                    }

                    //refund money to client
                    client.setMoney(client.getMoney() + theatre.getShow().getTicket().getPrice());

                    //update attendance list
                    clientAttend.put(entry.getKey(), false);

                    //update available seats
                    theatre.getShow().getSeat().setNrSeats(theatre.getShow().getSeat().getNrSeats() + 1);

                    //update seat list
                    Map<Integer, Boolean> sts = theatre.getShow().getSeat().getAllSeats();
                    for (Map.Entry<Integer, Boolean> ent : sts.entrySet()){
                        //find first unnocupied seat and set to free
                        if (ent.getValue()){
                            sts.put(ent.getKey(), false);
                            break;
                        }
                    }
                    return true;
                }
            }
        }


        return false;
    }

    public boolean cancelShow(Host host) throws FileNotFoundException {
        List<Theatre> theatres = csvService.getTheatres();

        Map<String, String> hostHosts = host.getShowsHost();

        // if host is not hosting any event
        if (hostHosts.size() == 0){
            return false;
        }


        for (Map.Entry<String, String> entry : hostHosts.entrySet()) {
            for (Theatre theatre : theatres){
                //if host is really host of this event
                if (entry.getKey().equals(theatre.getShow().getTicket().getShowName()) && theatre.getShow().getHasHost()
                        && theatre.getShow().getHost().getUsername().equals(host.getUsername())){
                    //we see if the show has been already hosted
                    Date date = new Date();

                    SimpleDateFormat ft =
                            new SimpleDateFormat ("yyyy MM dd");

                    String eventDate = "";
                    eventDate += theatre.getShow().getTicket().getYear() + " " + theatre.getShow().getTicket().getMonth()
                            + " " + theatre.getShow().getTicket().getDay();

                    System.out.println(eventDate);
                    // event already has been
                    if (ft.format(date).compareTo(eventDate) >= 0){
                        return false; //cant cancel show
                    }

                    //refund money to clients
                    List<Client> clients = csvService.getClients();
                    for (Client client : clients){
                        Map<String, Boolean> clsAttend = client.getShowsAttend();
                        for (Map.Entry<String, Boolean> ent : clsAttend.entrySet()){

                            System.out.println("here");
                            // if client is attending
                            if (ent.getKey().equals(theatre.getShow().getTicket().getShowName())
                                    && ent.getValue()){

                                client.setMoney(client.getMoney() + theatre.getShow().getTicket().getPrice());
                            }
                        }
                    }

                    //refund money to host
                    host.setMoney(host.getMoney() + theatre.getShow().getTicket().getPrice());

                    //and now deleting the show
                    List<String> thHosts = theatre.getShowsHosted();
                    List<String> newShows = new ArrayList<>();
                    for(String s : thHosts){
                        if (!s.equals(theatre.getShow().getTicket().getShowName()))
                                newShows.add(s);
                    }
                    //setting the new shows
                    theatre.setShowsHosted(newShows);

                    //done

                    return true;
                }
            }
        }

        return false;
    }
}
