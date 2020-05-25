package model.structure;

import model.event.Show;

import java.util.ArrayList;
import java.util.List;

public class Theatre extends Building {
    private Integer theatreId;
    private String name;
    private Show show;
    private String theatreLocation;
    private List<String> showsHosted = new ArrayList<>();

    public Theatre(Integer theatreId, Double surface, Integer height, String architecture, String name, Show show, String theatreLocation) {
        super(surface, height, architecture);
        this.theatreId = theatreId;
        this.name = name;
        this.show = show;
        this.theatreLocation = theatreLocation;
    }

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public String getTheatreLocation() {
        return theatreLocation;
    }

    public void setTheatreLocation(String theatreLocation) {
        this.theatreLocation = theatreLocation;
    }

    public List<String> getShowsHosted() {
        return showsHosted;
    }

    public void setShowsHosted(List<String> showsHosted) {
        this.showsHosted = showsHosted;
    }
}
