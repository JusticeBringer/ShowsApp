package GUI.clientArea;

import model.individual.Client;
import model.structure.Theatre;
import service.ShowService;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientFrame extends JFrame {

    private Client client;

    private String username;
    private Double userMoney = 0.0;
    private String spaceVeryVeryLittle = "     ";
    private String spaceVeryLittle = "                       ";
    private String spaceLittle = "                                                             ";
    private String spaceMedium = spaceLittle + " " + spaceLittle;
    private String spaceBig = spaceMedium + " " + spaceLittle;
    private String spaceVeryBig = spaceBig + " " + spaceMedium;
    private String showAllDetails = "";


    private String prShowName;
    private String prShowLocation;

    private JPanel clientPanel = new JPanel(new FlowLayout());
    private JLabel clientArea = new JLabel("");
    private JLabel greetUser = new JLabel( "Welcome, ");


    private JLabel insertMoney = new JLabel("Your money: ");
    private JLabel showsAvailable = new JLabel( "List of shows: " + spaceVeryLittle);


    private JLabel buyTi = new JLabel("Buy ticket at show (insert show number) ");
    private JTextArea jTextArea = new JTextArea(1, 1);
    private JButton buyTicket = new JButton("Buy ticket");

    private JLabel refundTi = new JLabel("           Refund ticket at show (insert show number) ");
    private JTextArea jTextArea2 = new JTextArea(1, 1);
    private JButton refundTicket = new JButton("Refund ticket");

    private boolean canGoNext = false;

    private static int showAfter = 0;

    private int howManyShows = 0;

    private ArrayList<String> st = new ArrayList<>();


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
        greetUser.setText(greetUser.getText() + username);
        clientPanel.add(greetUser, BorderLayout.LINE_START);

        insertMoney.setText(insertMoney.getText() + userMoney.toString());
        clientPanel.add(insertMoney);

        showsAvailable.setText(showsAvailable.getText() + getShowAllDetails());
        clientPanel.add(showsAvailable, BorderLayout.BEFORE_LINE_BEGINS);

        // add buy ticket
        clientPanel.add(buyTi);
        clientPanel.add(jTextArea);
        clientPanel.add(buyTicket);

        buyTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String getShowNrToBuy = jTextArea.getText();
                int getNrShowNrToBuy = Integer.parseInt(getShowNrToBuy);

                ShowService showService = new ShowService();

                boolean canBuy = showService.buyTicket(getClient(), getNrShowNrToBuy);
                int showMoney = showService.returnShowCost(getNrShowNrToBuy);

                if (canBuy){
                    // set new money amount
                    setUserMoney(getUserMoney() - showMoney);
                    insertMoney.setText("Your money: " + getUserMoney());

                    // execute update to database
                    UserService userService = new UserService();
                    userService.updateMoneyToClient(getClient().getClientId() , getUserMoney());


                }
                else{

                    // show dialog that he cannot buy

                    JPanel myPanel = new JPanel();
                    myPanel.setBounds(0, 0, 400, 50);
                    myPanel.setBackground(Color.WHITE);
                    JOptionPane jop = new JOptionPane();
                    JDialog dialog = jop.createDialog("Ticket already bought or no money");
                    dialog.setSize(400, 50);
                    dialog.setContentPane(myPanel);
                    dialog.setVisible(true);
                }
            }
        });

        // add refund ticket
        clientPanel.add(refundTi);
        clientPanel.add(jTextArea2);
        clientPanel.add(refundTicket);

        refundTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String getShowNrToBuy = jTextArea.getText();
                int getNrShowToRefund = Integer.parseInt(getShowNrToBuy);

                ShowService showService = new ShowService();

                boolean canRefund = showService.refundTicket(getClient(), getNrShowToRefund);
                int showMoney = showService.returnShowCost(getNrShowToRefund);

                if(canRefund){
                    // set new money amount

                    setUserMoney(getUserMoney() + showMoney);
                    insertMoney.setText("Your money: " + getUserMoney());

                    //execute update to database
                    UserService userService = new UserService();
                    userService.updateMoneyToClient(getClient().getClientId(), getUserMoney());

                }
                else{

                    // show dialog that we cannot refund

                    JPanel myPanel = new JPanel();
                    myPanel.setBounds(0, 0, 400, 50);
                    myPanel.setBackground(Color.WHITE);
                    JOptionPane jop = new JOptionPane();
                    JDialog dialog = jop.createDialog("Ticket already refunded or expired");
                    dialog.setSize(400, 50);
                    dialog.setContentPane(myPanel);
                    dialog.setVisible(true);
                }
            }
        });

        for(int i = 0; i < getHowManyShows(); i ++){
            JLabel putShowText = new JLabel();
            putShowText.setText(st.get(i));

            clientPanel.add(putShowText);
        }

        setSize(1700, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMoreShows(String showDet){
        this.st.add(showDet);
    }

    public ArrayList<String> getSt() {
        return st;
    }

    public void setSt(ArrayList<String> st) {
        this.st = st;
    }

    public int getHowManyShows() {
        return howManyShows;
    }

    public void setHowManyShows(int howManyShows) {
        this.howManyShows = howManyShows;
    }

    public String getShowAllDetails() {
        return showAllDetails;
    }

    public void setShowAllDetails(String showAllDetails) {
        this.showAllDetails = showAllDetails;
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