package model.event;

import model.individual.Host;

import java.util.List;

public class Show extends Seat{
    private Ticket ticket;
    private Host host;

    public Show(long nrSeats, List<Long> allSeats, Ticket ticket, Host host) {
        super(nrSeats, allSeats);
        this.ticket = ticket;
        this.host = host;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
}
