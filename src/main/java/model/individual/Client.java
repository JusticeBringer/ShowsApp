package model.individual;

import java.util.HashMap;
import java.util.Map;

public class Client extends Person {
    private Integer clientId;
    private double money;
    private Map<String, Boolean> showsAttend = new HashMap<>();
    private static int nrShowsAttend = 0;

    public Client(Integer clientId, String username, String password, String firstName, String familyName, String email,
                  Integer age,  double money) {

        super(username, password, firstName, familyName, email, age);
        this.clientId = clientId;
        this.money = money;
    }

    public Client(Integer clientId, double money) {
        this.clientId = clientId;
        this.money = money;
    }

    public Client(int id, String u, String p, double money) {
        super(id, u, p);
        this.clientId = id;
        this.money = money;
    }

    public Client(int id, String u, String p) {
        super(id, u, p);
        this.clientId = id;
    }
    public Client(String u, String p) {
        super(u, p);
        this.setUsername(u);
        this.setPassword(p);
    }

    public Client() {
        this.clientId = 0;
        this.money = 0;
    }

    public Map<String, Boolean> getShowsAttend() {
        return showsAttend;
    }

    public void setShowsAttend(Map<String, Boolean> showsAttend) {
        this.showsAttend = showsAttend;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public static int getNrShowsAttend() {
        return nrShowsAttend;
    }

    public static void setNrShowsAttend(int nrShowsAttend) {
        Client.nrShowsAttend = nrShowsAttend;
    }
}
