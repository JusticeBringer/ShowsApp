package model.individual;

import java.util.HashMap;
import java.util.Map;

public class Host extends Person {
    private Integer hostId;
    private double money;
    private Map<String, String> showsHost = new HashMap<>();
    private static int nrShowsHosted = 0;

    public Host(Integer hostId, String username, String password, String firstName, String familyName, String email, Integer age,  double money) {
        super(username, password, firstName, familyName, email, age);
        this.hostId = hostId;
        this.money = money;
    }

    public Host(Integer hostId, double money) {
        this.hostId = hostId;
        this.money = money;
    }

    public Host(int id, String u, String p) {
        super(id, u, p);
        this.hostId = id;
    }
    public Host(String u, String p) {
        super(u, p);
        this.setUsername(u);
        this.setPassword(p);
    }

    public Map<String, String> getShowsHost() {
        return showsHost;
    }

    public void setShowsHost(Map<String, String> showsHost) {
        this.showsHost = showsHost;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public static int getNrShowsHosted() {
        return nrShowsHosted;
    }

    public static void setNrShowsHosted(int nrShowsHosted) {
        Host.nrShowsHosted = nrShowsHosted;
    }
}
