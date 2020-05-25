package model.structure;

import model.event.Show;

public class Theatre extends Building {
    private String name;
    private Show show;
    private String thLocation;

    public Theatre(Double surface, Integer height, String architecture, String name, Show show, String thLocation) {
        super(surface, height, architecture);
        this.name = name;
        this.show = show;
        this.thLocation = thLocation;
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

    public String getThLocation() {
        return thLocation;
    }

    public void setThLocation(String thLocation) {
        this.thLocation = thLocation;
    }
}
