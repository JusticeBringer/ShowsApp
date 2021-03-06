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

public class ShowService{

    private DatabaseService databaseService;
    private AuditService auditService;

    public ShowService() {
        databaseService = new DatabaseService();
    }

    public String showSendDetails(int count) {
        List<Theatre> theatres = databaseService.getDBTheatres();
        String retSt = "";
        int nr = -1;

        for (Theatre t :
                theatres) {

            nr ++;

            if (nr != count){
                continue;
            }

            retSt +=  (nr)  + ". " + t.getShow().getTicket().getShowName() + " will be at " + t.getShow().getTicket().getShowLocation()
                    + " and hosted at " + t.getName() + " . ";
            if (t.getShow().getHasHost()){

                retSt += t.getShow().getTicket().getPrice() + "$ is this show ticket price. "
                        + "Show Date: " + t.getShow().getTicket().getDay()
                        + " " + t.getShow().getTicket().getMonth() + " " + t.getShow().getTicket().getYear()
                        + ". " + t.getShow().getHost().getFirstName() + " " + t.getShow().getHost().getFamilyName()
                        + " is organising the show. Contact him at this email address: "
                        + t.getShow().getHost().getEmail();
            }
            else{
                retSt += t.getShow().getTicket().getPrice() + "$ is this show ticket price. "
                        + "Show Date: " + t.getShow().getTicket().getDay()
                        + " " + t.getShow().getTicket().getMonth() + " " + t.getShow().getTicket().getYear();
            }

            int seatsAvailable = 0;
            Map<Integer, Boolean> seats = t.getShow().getSeat().getAllSeats();
            for (Map.Entry<Integer, Boolean> entry : seats.entrySet()) {
                if( !entry.getValue()) seatsAvailable ++;
            }

            if (t.getShow().getHasHost()){
                retSt += ". There are " + seatsAvailable + " available tickets. \n";
            }
            else{
                retSt += ". There are " + seatsAvailable + " available tickets. Not hosted yet.\n";
            }

        }
        return retSt;
    }

    public int returnShowCost(Integer showNr){

        List<Theatre> theatres = databaseService.getDBTheatres();
        return theatres.get(showNr).getShow().getTicket().getPrice();
    }

    public boolean buyTicket(Client client, Integer showToBuy) {
        List<Theatre> theatres = databaseService.getDBTheatres();

        int seatsAvailable = 0;
        Map<Integer, Boolean> seats = theatres.get(showToBuy).getShow().getSeat().getAllSeats();
        for (Map.Entry<Integer, Boolean> entry : seats.entrySet()) {
            if( !entry.getValue()) seatsAvailable ++;
        }

        if (seatsAvailable == 0){
            return false;
        }

        if(theatres.get(showToBuy).getShow().getTicket().getPrice() > client.getMoney()){
            return false;
        }

        // we test if the client is not already attending the event
        Map<String, Boolean> shClient = client.getShowsAttend();
        for (Map.Entry<String, Boolean> ent : shClient.entrySet()){
            if (ent.getKey().equals(theatres.get(showToBuy).getShow().getTicket().getShowName())){
                if(ent.getValue())
                    return false;
            }
        }

        // we update client money
        client.setMoney(client.getMoney() - theatres.get(showToBuy).getShow().getTicket().getPrice());

        // we set client attending to event
        shClient.put(theatres.get(showToBuy).getShow().getTicket().getShowName(), true);
        client.setShowsAttend(shClient);

        //update seats list
        Map<Integer, Boolean> sts = theatres.get(showToBuy).getShow().getSeat().getAllSeats();
        for (Map.Entry<Integer, Boolean> ent : sts.entrySet()){
            //find first unnocupied seat and set to occupied
            if (!ent.getValue()){
                sts.put(ent.getKey(), true);
                break;
            }
        }

        // we update number of seats
        theatres.get(showToBuy).getShow().getSeat().setNrSeats(theatres.get(showToBuy).getShow().getSeat().getNrSeats() - 1);

        // update database seats
        UserService userService = new UserService();
        userService.updateSeatsToShows(showToBuy + 1, theatres.get(showToBuy).getShow().getSeat().getNrSeats());

        return true;
    }

    public boolean hostEvent(Host host, Integer showToHost) {

        List<Theatre> theatres = databaseService.getDBTheatres();

        int seatsAvailable = 0;
        Map<Integer, Boolean> seats = theatres.get(showToHost).getShow().getSeat().getAllSeats();
        for (Map.Entry<Integer, Boolean> entry : seats.entrySet()) {
            if( !entry.getValue()) seatsAvailable ++;
        }

        if (seatsAvailable == 0){
            return false;
        }

        if(theatres.get(showToHost).getShow().getTicket().getPrice() > host.getMoney()){
            return false;
        }

        // we test if the host is not already hosting the event
        Map<String, String> hsShows = host.getShowsHost();
        for (Map.Entry<String, String> ent: hsShows.entrySet()) {
            if (ent.getKey().equals(theatres.get(showToHost).getShow().getTicket().getShowName())){
                if (ent.getValue().equals(host.getUsername())){
                    return false;
                }
            }
        }

        //if event already hosted
        if (theatres.get(showToHost).getShow().getHasHost()) return false;

        // we set the host of an event
        theatres.get(showToHost).getShow().setHasHost(true);

        // we add the event to the host events
        hsShows.put(theatres.get(showToHost).getShow().getTicket().getShowName(), host.getUsername());
        host.setShowsHost(hsShows);

        //update host price
        host.setMoney(host.getMoney() - theatres.get(showToHost).getShow().getTicket().getPrice());

        return true;
    }

    public boolean refundTicket(Client client, Integer showToRefund){
        List<Theatre> theatres = databaseService.getDBTheatres();

        Map<String, Boolean> clientAttend = client.getShowsAttend();

        // if user is not attending any event
        if (clientAttend.size() == 0){
            return false;
        }

        // we test if the client is not attending the event
        for (Map.Entry<String, Boolean> ent : clientAttend.entrySet()){
            if (ent.getKey().equals(theatres.get(showToRefund).getShow().getTicket().getShowName())){
                if(!ent.getValue())
                    return false;
            }
        }

        //we see if the show has been already hosted
        Date date = new Date();

        SimpleDateFormat ft =
                new SimpleDateFormat ("yyyy MM dd");

        String eventDate = "";
        eventDate += theatres.get(showToRefund).getShow().getTicket().getYear() + " " + theatres.get(showToRefund).getShow().getTicket().getMonth()
                + " " + theatres.get(showToRefund).getShow().getTicket().getDay();

        // event already has been
        if (ft.format(date).compareTo(eventDate) >= 0){
            return false; //cant cancel ticket
        }

        //refund money to client
        client.setMoney(client.getMoney() + theatres.get(showToRefund).getShow().getTicket().getPrice());

        //update attendance list
        clientAttend.put(theatres.get(showToRefund).getShow().getTicket().getShowName(), false);

        //update available seats
        theatres.get(showToRefund).getShow().getSeat().setNrSeats(theatres.get(showToRefund).getShow().getSeat().getNrSeats() + 1);

        // update database seats
        UserService userService = new UserService();
        userService.updateSeatsToShows(showToRefund + 1, theatres.get(showToRefund).getShow().getSeat().getNrSeats());

        //update seat list
        Map<Integer, Boolean> sts = theatres.get(showToRefund).getShow().getSeat().getAllSeats();
        for (Map.Entry<Integer, Boolean> ent : sts.entrySet()){
            //find first unnocupied seat and set to free
            if (ent.getValue()){
                sts.put(ent.getKey(), false);
                break;
            }
        }
        return true;

    }

    public boolean cancelShow(Host host, int getNrShowNrToCancel) {
        List<Theatre> theatres = databaseService.getDBTheatres();

        Map<String, String> hostHosts = host.getShowsHost();

        // if host is not hosting any event
        if (hostHosts.size() == 0){
            return false;
        }

        // we test if the host is not hosting the show
        for (Map.Entry<String, String> entry : hostHosts.entrySet()) {
            if (entry.getKey().equals(theatres.get(getNrShowNrToCancel).getShow().getTicket().getShowName())){
                if (!entry.getValue().equals(host.getUsername())){
                    return false;
                }
            }
        }

        //we see if the show has been already hosted
        Date date = new Date();

        SimpleDateFormat ft =
                new SimpleDateFormat ("yyyy MM dd");

        String eventDate = "";
        eventDate += theatres.get(getNrShowNrToCancel).getShow().getTicket().getYear() + " " + theatres.get(getNrShowNrToCancel).getShow().getTicket().getMonth()
                + " " + theatres.get(getNrShowNrToCancel).getShow().getTicket().getDay();

        // event already has been
        if (ft.format(date).compareTo(eventDate) >= 0){
            return false; //cant cancel show
        }

        UserService userService = new UserService();

        //refund money to clients
        List<Client> clients = databaseService.getDBClients();
        for (Client client : clients){
            // refunding money to clients - Lack of database column with shows that client attends -> refund all clients

            client.setMoney(client.getMoney() + theatres.get(getNrShowNrToCancel).getShow().getTicket().getPrice());
            // database update
            userService.updateMoneyToClient(client.getClientId() , client.getMoney());

        }

        // refund money to host
        host.setMoney(host.getMoney() + theatres.get(getNrShowNrToCancel).getShow().getTicket().getPrice());
        // database update
        userService.updateMoneyToHost(host.getHostId(), host.getMoney());

        //and now deleting the show
        List<String> thHosts = theatres.get(getNrShowNrToCancel).getShowsHosted();
        List<String> newShows = new ArrayList<>();
        for(String s : thHosts){
            if (!s.equals(theatres.get(getNrShowNrToCancel).getShow().getTicket().getShowName()))
                newShows.add(s);
        }

        //setting the new shows
        theatres.get(getNrShowNrToCancel).setShowsHosted(newShows);

        // update database for events

        // delete from show
        userService.deleteShowFromId(getNrShowNrToCancel);

        // delete from ticket
        userService.deleteTicketFromId(getNrShowNrToCancel);

        // delete from theatre - modified - cancelling show no longer deletes theatre
        // userService.deleteTheatreFromId(getNrShowNrToCancel);

        return false;
    }
}
