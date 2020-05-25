package model.event;

import java.util.HashMap;
import java.util.Map;

public class Seat {
    private Integer nrSeats;
    private Map<Integer, Boolean> allSeats;

    public Seat(Integer nrSeats, Map<Integer, Boolean> allSeats) {
        this.nrSeats = nrSeats;
        this.allSeats = allSeats;
    }

    public Seat(Integer nrSeats) {
        this.nrSeats = nrSeats;
        this.allSeats = new HashMap<>();

        // place nrSeats at show
        for (int i = 0; i < nrSeats; i++){
            allSeats.put(i, false);
        }
    }

    public Integer getNrSeats() {
        return nrSeats;
    }

    public void setNrSeats(Integer nrSeats) {
        this.nrSeats = nrSeats;
    }

    public Map<Integer, Boolean> getAllSeats() {
        return allSeats;
    }

    public void setAllSeats(Map<Integer, Boolean> allSeats) {
        this.allSeats = allSeats;
    }
}
