package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    public JTextField txtEmail;
    public JPasswordField txtPassword;
    public JButton btnLogin;

    public LoginView() {
        setTitle("SportEase - Login");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        btnLogin = new JButton("Login");
        panel.add(new JLabel(""));
        panel.add(btnLogin);

        add(panel);
        setVisible(true);
    }
}
