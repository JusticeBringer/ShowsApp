package model.individual;

public class Client extends Person {
    private Integer clientId;
    private double money;
    private static int nrShowsAttend = 0;

    public Client(Integer clientId, String username, String password, String firstName, String familyName, String email, Integer age,  double money) {
        super(username, password, firstName, familyName, email, age);
        this.clientId = clientId;
        this.money = money;
    }

    public Client(Integer clientId, double money) {
        this.clientId = clientId;
        this.money = money;
    }

    public Client(int id, String u, String p, Integer clientId, double money) {
        super(id, u, p);
        this.clientId = clientId;
        this.money = money;
    }

    public Client() {
        this.clientId = 0;
        this.money = 0;
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
