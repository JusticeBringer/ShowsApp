package loginArea;

import model.individual.Client;
import model.individual.Host;
import service.LoginService;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private String username;

    private JPanel loginPanel = new JPanel(new FlowLayout());
    private JLabel welcomeText = new JLabel("                 Welcome to Shows App              ");
    private JLabel insertUsername = new JLabel("Username");
    private JLabel newLine = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine1 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine2 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine3 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine4 = new JLabel("<html><br><br><p></p></html>");
    private JLabel newLine5 = new JLabel("<html><br><br><p></p></html>");
    private JTextArea inputUsername = new JTextArea(2, 20);
    private JLabel insertPassword = new JLabel("Password");
    private JPasswordField inputPassword = new JPasswordField(20);
    private JRadioButton clientOrHost = new JRadioButton("Client if checked, Host otherwise");
    private JButton loginButton = new JButton("Login");

    public LoginFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(loginPanel);
        loginPanel.add(welcomeText);
        loginPanel.add(newLine);
        loginPanel.add(insertUsername);
        loginPanel.add(newLine1);
        loginPanel.add(inputUsername);
        loginPanel.add(newLine2);
        loginPanel.add(insertPassword);
        loginPanel.add(newLine3);
        loginPanel.add(inputPassword);
        loginPanel.add(newLine4);
        loginPanel.add(clientOrHost);
        loginPanel.add(newLine5);
        loginPanel.add(loginButton);

        loginButtonAction();

        setSize(300,350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void loginButtonAction(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String usr = inputUsername.getText();
                String psw = String.valueOf(inputPassword.getPassword());
                boolean checkBx =  clientOrHost.isSelected();

                System.out.println(usr + " " + psw + " " + checkBx);

                LoginService loginService = new LoginService();
                boolean res1 = false;

                if(checkBx){
                    Client c = new Client(usr, psw);
                    res1 = loginService.loginClient(c);
                }
                else{
                    Host h = new Host(usr, psw);
                    res1 = loginService.loginHost(h);
                }

                System.out.println(res1);
            }
        });
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(JPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public JLabel getWelcomeText() {
        return welcomeText;
    }

    public void setWelcomeText(JLabel welcomeText) {
        this.welcomeText = welcomeText;
    }

    public JLabel getInsertUsername() {
        return insertUsername;
    }

    public void setInsertUsername(JLabel insertUsername) {
        this.insertUsername = insertUsername;
    }

    public JLabel getNewLine() {
        return newLine;
    }

    public void setNewLine(JLabel newLine) {
        this.newLine = newLine;
    }

    public JLabel getNewLine1() {
        return newLine1;
    }

    public void setNewLine1(JLabel newLine1) {
        this.newLine1 = newLine1;
    }

    public JLabel getNewLine2() {
        return newLine2;
    }

    public void setNewLine2(JLabel newLine2) {
        this.newLine2 = newLine2;
    }

    public JLabel getNewLine3() {
        return newLine3;
    }

    public void setNewLine3(JLabel newLine3) {
        this.newLine3 = newLine3;
    }

    public JLabel getNewLine4() {
        return newLine4;
    }

    public void setNewLine4(JLabel newLine4) {
        this.newLine4 = newLine4;
    }

    public JLabel getNewLine5() {
        return newLine5;
    }

    public void setNewLine5(JLabel newLine5) {
        this.newLine5 = newLine5;
    }

    public JTextArea getInputUsername() {
        return inputUsername;
    }

    public void setInputUsername(JTextArea inputUsername) {
        this.inputUsername = inputUsername;
    }

    public JLabel getInsertPassword() {
        return insertPassword;
    }

    public void setInsertPassword(JLabel insertPassword) {
        this.insertPassword = insertPassword;
    }

    public JPasswordField getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(JPasswordField inputPassword) {
        this.inputPassword = inputPassword;
    }

    public JRadioButton getClientOrHost() {
        return clientOrHost;
    }

    public void setClientOrHost(JRadioButton clientOrHost) {
        this.clientOrHost = clientOrHost;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }



}
