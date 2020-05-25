package model.individual;

public class Host extends Person {
    private double money;
    private static int nrShowsHosted = 0;

    public Host(String firstName, String familyName, Integer age, double money) {
        super(firstName, familyName, age);
        this.money = money;
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
