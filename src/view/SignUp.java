package view;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class SignUp extends javax.swing.JFrame {    

    public SignUp() {
        initComponents();   

        // Button actions
        btnSignUp.addActionListener(e -> handleSignUp());
        btnLogin.addActionListener(e -> {
            new Login().setVisible(true);
            this.dispose();
        });
    }

    private void handleSignUp() {
        String fullname = fullName.getText().trim();
        String emailValue = email.getText().trim();
        String passwordValue = new String(passwordField.getPassword());
        String questionValue = question.getSelectedItem().toString();
        String answerValue = answer.getText().trim();

        if(fullname.isEmpty() || emailValue.isEmpty() || passwordValue.isEmpty() || answerValue.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = controller.SignUpController.registerUser(fullname, emailValue, passwordValue, questionValue, answerValue);

        if(success){
            JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JLabel();
        fullName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        email = new javax.swing.JTextField();
        txtPassword = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cmbSecurityQuestion = new javax.swing.JLabel();
        question = new javax.swing.JComboBox<>();
        txtAnswer = new javax.swing.JLabel();
        answer = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(null);

        jPanel4.setPreferredSize(new java.awt.Dimension(570, 450));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24));
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("Sign Up");

        txtFullName.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        txtFullName.setText("Full Name");

        txtEmail.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        txtEmail.setText("Email");

        txtPassword.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        txtPassword.setText("Password");

        jLabel6.setText("Already have an account? ");

        btnLogin.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
        btnLogin.setForeground(new java.awt.Color(153, 0, 0));
        btnLogin.setText("Log In");

        btnSignUp.setBackground(new java.awt.Color(153, 0, 0));
        btnSignUp.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
        btnSignUp.setForeground(new java.awt.Color(255, 255, 255));
        btnSignUp.setText("Sign Up");

        jLabel5.setText("Welcome to SportEase! Get ready to book your favourite venues with ease!");

        cmbSecurityQuestion.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        cmbSecurityQuestion.setText("Security Question");

        question.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { 
            "What is the name of your first pet?", 
            "Which school did you pass SEE?", 
            "What is name of town you were born in?", 
            "What is your nickname?" 
        }));

        txtAnswer.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        txtAnswer.setText("Answer");

        answer.addActionListener(evt -> {});

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 97, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword)
                    .addComponent(txtEmail)
                    .addComponent(txtFullName)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cmbSecurityQuestion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(question, 0, 274, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtAnswer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(answer))
                    .addComponent(passwordField)
                    .addComponent(email)
                    .addComponent(fullName)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLogin))
                    .addComponent(btnSignUp))
                .addGap(74, 74, 74))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFullName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSecurityQuestion)
                    .addComponent(question, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnswer)
                    .addComponent(answer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSignUp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnLogin))
                .addContainerGap())
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(100, 20, 570, 450);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 740, 490);

        pack();
    }

    // Variables declaration
    private javax.swing.JTextField answer;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel cmbSecurityQuestion;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fullName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JComboBox<String> question;
    private javax.swing.JLabel txtAnswer;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtFullName;
    private javax.swing.JLabel txtPassword;

    // ActionListener helpers
    public void addSignUpListener(ActionListener listener) {
        btnSignUp.addActionListener(listener);
    }

    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    // Getters
    public String getFullName() {
        return fullName.getText().trim();
    }

    public String getEmail() {
        return email.getText().trim();
    }

    public String getSelectedItem() {
        return question.getSelectedItem().toString();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getAnswer() {
        return answer.getText().trim();
    }
}
