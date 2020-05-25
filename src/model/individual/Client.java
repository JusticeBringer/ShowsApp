package model.individual;

public class Client extends Person {
    private double money;
    private static int nrShowsAttend = 0;

    public Client(String firstName, String familyName, Integer age, double money) {
        super(firstName, familyName, age);
        this.money = money;
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
