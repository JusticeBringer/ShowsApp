package GUI.hostArea;

import javax.swing.*;
import java.awt.*;

public class HostFrame extends JFrame {

    private String username;
    private Double userMoney = 0.0;
    private String spaceVeryLittle = "                       ";
    private String spaceLittle = "                                                             ";
    private String spaceMedium = spaceLittle + " " + spaceLittle;
    private String spaceBig = spaceMedium + " " + spaceLittle;
    private String spaceVeryBig = spaceBig + " " + spaceMedium;

    private JPanel hostPanel = new JPanel(new FlowLayout());
    private JLabel hostArea = new JLabel(spaceLittle + "             " + spaceMedium);
    private JLabel greetUser = new JLabel(spaceLittle + "Welcome, ");
    private JLabel newLine = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine1 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine2 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine3 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine4 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine5 = new JLabel("<html><br><br><p></p></html>");

    private boolean canGoNext = false;

    private JLabel sp = new JLabel(spaceMedium);

    private JLabel insertMoney = new JLabel("Your money: ");
    private JLabel showsAvailable = new JLabel( "List of shows: " + spaceVeryLittle);

    private JButton buyTicket = new JButton("Host show");

    private static int showAfter = 0;

    public HostFrame() {
        if (showAfter > 0){
            showThePanel();
        }

        showAfter ++;
    }
    public void showThePanel(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(hostPanel);
        hostPanel.add(hostArea);

        hostPanel.add(newLine);
        greetUser.setText(greetUser.getText() + username + spaceLittle);
        hostPanel.add(greetUser, BorderLayout.LINE_START);

        hostPanel.add(newLine1);
        hostPanel.add(newLine2);
        hostPanel.add(newLine3);

        hostPanel.add(showsAvailable, BorderLayout.BEFORE_LINE_BEGINS);
        hostPanel.add(newLine4);
        hostPanel.add(newLine5);

        insertMoney.setText(insertMoney.getText() + userMoney.toString());
        hostPanel.add(insertMoney, BorderLayout.AFTER_LINE_ENDS);

        hostPanel.add(sp);

        setSize(500, 450);
        setLocationRelativeTo(null);
        setVisible(true);
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