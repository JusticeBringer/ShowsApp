package model.event;

import java.util.ArrayList;
import java.util.List;

public class Seat {
    private Long nrSeats;
    private List<Long> allSeats = new ArrayList<>();

    public Seat(long nrSeats, List<Long> allSeats) {
        this.nrSeats = nrSeats;
        this.allSeats = allSeats;
    }

    public long getNrSeats() {
        return nrSeats;
    }

    public void setNrSeats(long nrSeats) {
        this.nrSeats = nrSeats;
    }

    public List<Long> getAllSeats() {
        return allSeats;
    }

    public void setAllSeats(List<Long> allSeats) {
        this.allSeats = allSeats;
    }
}
