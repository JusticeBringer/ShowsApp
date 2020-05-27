package model.event;

public class Ticket {
    private Integer ticketId;
    private Integer price;
    private Integer year;
    private Integer month;
    private Integer day;
    private String showName;
    private String showLocation;

    public Ticket(Integer ticketId, Integer price, Integer year, Integer month, Integer day,
                  String showName, String showLocation) {
        this.ticketId = ticketId;
        this.price = price;
        this.year = year;
        this.month = month;
        this.day = day;
        this.showName = showName;
        this.showLocation = showLocation;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowLocation() {
        return showLocation;
    }

    public void setShowLocation(String showLocation) {
        this.showLocation = showLocation;
    }
}
