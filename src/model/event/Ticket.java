package model.event;

public class Ticket {
    private Integer price;
    private Integer year;
    private Integer month;
    private Integer day;
    private String location;

    public Ticket(Integer price, Integer year, Integer month, Integer day, String location) {
        this.price = price;
        this.year = year;
        this.month = month;
        this.day = day;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
