package view;

import controller.LoginController;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author aaisma
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();

        // Log In button
        btnLogin.addActionListener(e -> handleLogin());

        // Sign Up button
        btnSignUp.addActionListener(e -> {
            new SignUp().setVisible(true);
            this.dispose();
        });

        // Forgot Password button
        btnForgetPassword.addActionListener(e -> {
            ForgetPassword forgotPage = new ForgetPassword();
            forgotPage.setVisible(true);
            forgotPage.setLocationRelativeTo(null);
            this.dispose();
        });
    }

    private void handleLogin() {
        String name = fullName.getText().trim();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both name and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Use LoginController to validate user
        String role = LoginController.validateUser(name, password);

        if (role == null) {
            JOptionPane.showMessageDialog(this, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (role) {
            case "ADMIN" -> new AdminDashboard().setVisible(true);
            case "USER" -> new UserDashboard().setVisible(true);
            default -> new Home().setVisible(true);
        }

        this.dispose();
    }

    // Optional: direct database login check
    private boolean checkLogin(String email, String password) {
        String url = "jdbc:mysql://localhost:3306/userdb";
        String user = "root";
        String pass = "Nanu@2062";

        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Database Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JLabel();
        fullName = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        txtPassword = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnForgetPassword = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setRequestFocusEnabled(false);

        jPanel4.setPreferredSize(new java.awt.Dimension(570, 450));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24));
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("Login");
        jLabel1.setPreferredSize(new java.awt.Dimension(114, 45));

        txtFullName.setBackground(new java.awt.Color(102, 102, 102));
        txtFullName.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        txtFullName.setText("Full Name ");
        txtFullName.setRequestFocusEnabled(false);

        fullName.setForeground(new java.awt.Color(102, 102, 102));

        passwordField.setForeground(new java.awt.Color(102, 102, 102));
        passwordField.setPreferredSize(new java.awt.Dimension(64, 24));

        txtPassword.setBackground(new java.awt.Color(102, 102, 102));
        txtPassword.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        txtPassword.setText("Password");

        jLabel6.setText("Already have an account? ");

        btnLogin.setBackground(new java.awt.Color(153, 0, 0));
        btnLogin.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Log In");

        btnSignUp.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
        btnSignUp.setForeground(new java.awt.Color(153, 0, 0));
        btnSignUp.setText("Sign Up");

        jLabel5.setText("Welcome back! to SportEase! Log in to book your next venue and stay in action!");

        btnForgetPassword.setForeground(new java.awt.Color(153, 0, 0));
        btnForgetPassword.setText("Forgot Password");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 73, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPassword)
                            .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnForgetPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fullName))
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(237, 237, 237))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSignUp)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(25, 25, 25)
                .addComponent(txtFullName)
                .addGap(18, 18, 18)
                .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPassword)
                .addGap(18, 18, 18)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnForgetPassword)
                .addGap(21, 21, 21)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 780, 460);

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnForgetPassword;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JTextField fullName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel txtFullName;
    private javax.swing.JLabel txtPassword;
    // End of variables declaration

    // ActionListener helpers
    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    public void addSignUpListener(ActionListener listener) {
        btnSignUp.addActionListener(listener);
    }

    public void addForgotPasswordListener(ActionListener listener) {
        btnForgetPassword.addActionListener(listener);
    }

    // Getters
    public String getFullName() {
        return fullName.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}
