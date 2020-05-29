package GUI.hostArea;

import model.event.Show;
import model.individual.Host;
import service.AuditService;
import service.ShowService;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HostFrame extends JFrame {

    private String username;
    private Double userMoney = 0.0;
    private String spaceVeryLittle = "                       ";
    private String spaceLittle = "                                                             ";
    private String spaceMedium = spaceLittle + " " + spaceLittle;
    private String spaceBig = spaceMedium + " " + spaceLittle;
    private String spaceVeryBig = spaceBig + " " + spaceMedium;

    private JPanel hostPanel = new JPanel(new FlowLayout());
    private JLabel hostArea = new JLabel("");
    private JLabel greetUser = new JLabel(spaceLittle + "Welcome, ");
    private JLabel newLine = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine1 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine2 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine3 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine4 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine5 = new JLabel("<html><br><br><p></p></html>");

    private boolean canGoNext = false;

    private JLabel sp = new JLabel(spaceMedium);

    // New Panel

    private Host host;

    private JLabel insertMoney = new JLabel("Your money: ");
    private JLabel showsAvailable = new JLabel( "List of shows: " + spaceVeryLittle);


    private JLabel hostShowText = new JLabel("Host show (insert show number) ");
    private JTextArea hostShowTextArea = new JTextArea(1, 1);
    private JButton hostShowButton = new JButton("Host show");

    private JLabel cancelShowText = new JLabel("           Cancel show (insert show number) ");
    private JTextArea cancelShowTextArea = new JTextArea(1,1);
    private JButton cancelShowButton = new JButton("Cancel show");

    private int howManyShows = 0;
    private ArrayList<String> st = new ArrayList<>();
    private ArrayList<String> stHosted = new ArrayList<>();

    private ArrayList<Integer> showsHostedOrNot = new ArrayList<>();
    private ArrayList<Integer> wasOrNotHostedAlready = new ArrayList<>();

    private static int showAfter = 0;

    private String showAllDetails = "";
    private AuditService auditService = new AuditService();

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

        greetUser.setText(greetUser.getText() + username);
        hostPanel.add(greetUser, BorderLayout.LINE_START);

        insertMoney.setText(insertMoney.getText() + userMoney.toString());
        hostPanel.add(insertMoney);

        showsAvailable.setText(showsAvailable.getText() + getShowAllDetails());
        hostPanel.add(showsAvailable, BorderLayout.BEFORE_LINE_BEGINS);

        // add host show
        hostPanel.add(hostShowText);
        hostPanel.add(hostShowTextArea);
        hostPanel.add(hostShowButton);

        hostShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String getShowNrToHost = hostShowTextArea.getText();
                int getNrShowNrToHost = Integer.parseInt(getShowNrToHost);

                ShowService showService = new ShowService();

                boolean canHost = showService.hostEvent(getHost(), getNrShowNrToHost);
                int showMoney = showService.returnShowCost(getNrShowNrToHost);

                if (canHost){
                    auditService.writeInAuditFile("Host successfully hosted a show", Thread.currentThread().getName());

                    // set new money amount of host
                    setUserMoney(getUserMoney() - showMoney);
                    insertMoney.setText("Your money: " + getUserMoney());

                    //Get the components in the panel
                    Component[] componentList = hostPanel.getComponents();

                    int nr = 0;

                    //Loop through the components
                    for(Component c : componentList){
                        //Find the components you want to remove
                        if(c instanceof JLabel){
                            nr ++;

                            if (nr < 7)
                                continue;

                            //Remove it
                            hostPanel.remove(c);
                        }
                    }

                    // update shows with host
                    updateShowsWithHost(getNrShowNrToHost);

                    // we update the text of hosted or not shows
                    setHostedWho();

                    // make new text
                    for(int i = 0; i < getHowManyShows(); i ++){
                        // set new text
                        JLabel putShowText = new JLabel();

                        // put updated text
                        putShowText.setText(stHosted.get(i));

                        // add to panel
                        hostPanel.add(putShowText);
                    }

                    // remake panel
                    hostPanel.revalidate();
                    hostPanel.repaint();

                    // execute update to database
                    UserService userService = new UserService();
                    userService.updateMoneyToHost(getHost().getHostId(), getUserMoney());
                }
                else{
                    // show dialog that he cannot host
                    auditService.writeInAuditFile("Host could not host event", Thread.currentThread().getName());

                    JPanel myPanel = new JPanel();
                    myPanel.setBounds(0, 0, 400, 50);
                    myPanel.setBackground(Color.WHITE);
                    JOptionPane jop = new JOptionPane();
                    JDialog dialog = jop.createDialog("Show already hosted or no money");
                    dialog.setSize(400, 50);
                    dialog.setContentPane(myPanel);
                    dialog.setVisible(true);
                }
            }
        });

        // add cancel show
        hostPanel.add(cancelShowText);
        hostPanel.add(cancelShowTextArea);
        hostPanel.add(cancelShowButton);

        cancelShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String getShowNrToCancel = hostShowTextArea.getText();
                int getNrShowNrToCancel = Integer.parseInt(getShowNrToCancel);

                ShowService showService = new ShowService();

                // set new money amount of host
                int showMoney = showService.returnShowCost(getNrShowNrToCancel);

                showService.cancelShow(getHost(), getNrShowNrToCancel);
                auditService.writeInAuditFile("Host is trying to cancel a show", Thread.currentThread().getName());


                setUserMoney(getUserMoney() + showMoney);
                insertMoney.setText("Your money: " + getUserMoney());

                //Get the components in the panel
                Component[] componentList = hostPanel.getComponents();

                int nr = 0;

                //Loop through the components
                for(Component c : componentList){
                    //Find the components you want to remove
                    if(c instanceof JLabel){
                        nr ++;

                        if (nr < 7)
                            continue;

                        //Remove it
                        hostPanel.remove(c);
                    }
                }

                // all database updates are in actual service calls

                setNewShowsAfterCancelledShow(getNrShowNrToCancel);

                // make new text
                for(int i = 0; i < st.size(); i ++){
                    // set new text
                    JLabel putShowText = new JLabel();

                    // put updated text
                    putShowText.setText(st.get(i));

                    // add to panel
                    hostPanel.add(putShowText);
                }

                // remake panel
                hostPanel.revalidate();
                hostPanel.repaint();
            }
        });

        for(int i = 0; i < getHowManyShows(); i ++){
            JLabel putShowText = new JLabel();
            putShowText.setText(st.get(i));

            hostPanel.add(putShowText);
        }


        setSize(1700, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateShowsWithHost(int getNrShowNrToHost) {
        showsHostedOrNot.add(getNrShowNrToHost);
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    private String getShowAllDetails() {
        return showAllDetails;
    }
    public void setShowAllDetails(String showAllDetails) {
        this.showAllDetails = showAllDetails;
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
    public int getHowManyShows() {
        return howManyShows;
    }

    public void setHowManyShows(int howManyShows) {
        this.howManyShows = howManyShows;
    }

    public void setMoreShows(String showDet) {
        this.st.add(showDet);
    }
    public ArrayList<String> getSt() {
        return st;
    }

    public ArrayList<String> getStHosted() {
        return stHosted;
    }

    public ArrayList<Integer> getShowsHostedOrNot() {
        return showsHostedOrNot;
    }

    public ArrayList<Integer> getWasOrNotHostedAlready() {
        return wasOrNotHostedAlready;
    }

    public void setWasOrNotHostedAlready(ArrayList<Integer> wasOrNotHostedAlready) {
        this.wasOrNotHostedAlready = wasOrNotHostedAlready;
    }

    public void setShowsHostedOrNot() {
        this.showsHostedOrNot = new ArrayList<>();
    }

    public void setNewShowsAfterCancelledShow(int getNrShowNrToCancel){
        for (int i = 0; i < st.size(); i++){
            if (getNrShowNrToCancel == i){
                st.remove(i);
            }
        }
    }

    // showHostedOrNot has shows hosted dynamic -> 1, 3 ...
    // was hosted has original values

    public void setHostedWho(){
        // we delete old values
        while (this.stHosted.size() > 0){
            this.stHosted.remove(0);
        }

        // we put the new values
        for(int i = 0; i < st.size(); i++){

            int sz = stHosted.size();

            // we search in newly hosted first
            for (int j = 0; j < showsHostedOrNot.size(); j++){
                if( (i) == showsHostedOrNot.get(j)){
                    stHosted.add(st.get(i) + " Now it is hosted");
                }
            }

            if (sz == stHosted.size()){
                stHosted.add(st.get(i));
            }
        }

    }
}