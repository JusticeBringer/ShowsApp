package GUI.clientArea;

import model.structure.Theatre;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClientFrame extends JFrame {

    private String username;
    private Double userMoney = 0.0;
    private String spaceVeryVeryLittle = "     ";
    private String spaceVeryLittle = "                       ";
    private String spaceLittle = "                                                             ";
    private String spaceMedium = spaceLittle + " " + spaceLittle;
    private String spaceBig = spaceMedium + " " + spaceLittle;
    private String spaceVeryBig = spaceBig + " " + spaceMedium;

    private String prShowName;
    private String prShowLocation;

    private JPanel clientPanel = new JPanel(new FlowLayout());
    private JLabel clientArea = new JLabel(spaceLittle + "             " + spaceMedium);
    private JLabel greetUser = new JLabel(spaceLittle + "Welcome, ");
    private JLabel newLine = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine1 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine2 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine3 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine4 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine5 = new JLabel("<html><br><br><p></p></html>");

    private JLabel sp = new JLabel(spaceMedium);

    private JLabel showName = new JLabel( spaceVeryBig + "\n\n . ");
    private JLabel showCity = new JLabel(spaceMedium + "Show Name" + spaceVeryLittle + "Location");
    private JLabel showTheatre = new JLabel(spaceVeryLittle + "Theatre");
    private JLabel showPrice = new JLabel(spaceVeryLittle + "Price");
    private JLabel showDate = new JLabel(spaceVeryLittle + "Date");
    private JLabel showHost = new JLabel(spaceVeryLittle + "Host");
    private JLabel showHostEmail = new JLabel(spaceVeryLittle + "Contact");
    private JLabel showTickets = new JLabel(spaceVeryLittle + "Tickets");
    private JLabel buyTi = new JLabel(spaceVeryLittle + "Buy ticket");

    private JLabel endLine = new JLabel("<html><br/></html>", SwingConstants.CENTER);

    private JLabel insertMoney = new JLabel("Your money: ");
    private JLabel showsAvailable = new JLabel( "List of shows: " + spaceVeryLittle);


    private JButton buyTicket = new JButton("Buy ticket");

    private JButton buyTicketFirst = new JButton("Buy ticket Show 1");
    private JButton buyTicketSecond = new JButton("Buy ticket Show 2");
    private JButton buyTicketThird = new JButton("Buy ticket Show 3");
    private JButton buyTicketFourth = new JButton("Buy ticket Show 4");

    private JTextArea jTextArea = new JTextArea(1, 1);

    private boolean canGoNext = false;

    private static int showAfter = 0;


    public ClientFrame() {
        if (showAfter > 0){
            showThePanel();
        }

        showAfter ++;
    }

    public void showThePanel(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(clientPanel);
        clientPanel.add(clientArea);

        clientPanel.add(newLine);
        greetUser.setText(greetUser.getText() + username + spaceLittle);
        clientPanel.add(greetUser, BorderLayout.LINE_START);

        insertMoney.setText(insertMoney.getText() + userMoney.toString());
        clientPanel.add(insertMoney);

        clientPanel.add(newLine1);
        clientPanel.add(newLine2);
        clientPanel.add(newLine3);

        clientPanel.add(showsAvailable, BorderLayout.BEFORE_LINE_BEGINS);
        clientPanel.add(newLine4);
        clientPanel.add(newLine5);


        clientPanel.add(showName);
        clientPanel.add(showCity);
        clientPanel.add(showTheatre);
        clientPanel.add(showPrice);
        clientPanel.add(showDate);
        clientPanel.add(showHost);
        clientPanel.add(showHostEmail);
        clientPanel.add(showTickets);
        clientPanel.add(buyTi);


        clientPanel.add(jTextArea);

        // add buy 4 buttons
        clientPanel.add(buyTicketFirst);
        clientPanel.add(buyTicketSecond);
        clientPanel.add(buyTicketThird);
        clientPanel.add(buyTicketFourth);

        // first show
        showName.setText(getPrShowName());
        showCity.setText(getPrShowLocation());

        clientPanel.add(showName);
        clientPanel.add(showCity);

        setSize(1400, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public String getPrShowName() {
        return prShowName;
    }

    public void setPrShowName(String prShowName) {
        this.prShowName = prShowName;
    }

    public String getPrShowLocation() {
        return prShowLocation;
    }

    public void setPrShowLocation(String prShowLocation) {
        this.prShowLocation = prShowLocation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(Double userMoney) {
        this.userMoney = userMoney;
    }
}