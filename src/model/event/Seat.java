package model.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seat {
    private Integer nrSeats;
    private Map<Integer, Boolean> allSeats = new HashMap<>();

    public Seat(Integer nrSeats, Map<Integer, Boolean> allSeats) {
        this.nrSeats = nrSeats;
        this.allSeats = allSeats;
    }

    public Seat(Integer nrSeats) {
        this.nrSeats = nrSeats;
        this.allSeats = new HashMap<>();
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
