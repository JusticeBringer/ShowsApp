package model.event;

import model.individual.Host;

import java.util.List;

public class Show{
    private Integer showId;
    private Seat seat;
    private Ticket ticket;
    private Host host;
    private Boolean hasHost;

    public Show(Integer showId, Seat seat, Ticket ticket, Host host) {
        this.showId = showId;
        this.seat = seat;
        this.ticket = ticket;
        this.host = host;
        this.hasHost = true;
    }

    public Show(Integer showId, Seat seat, Ticket ticket) {
        this.showId = showId;
        this.seat = seat;
        this.ticket = ticket;
        this.hasHost = false;
    }

    public Boolean getHasHost() {
        return hasHost;
    }

    public void setHasHost(Boolean hasHost) {
        this.hasHost = hasHost;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
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
