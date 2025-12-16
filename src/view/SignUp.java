package view;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class SignUp extends javax.swing.JFrame {    

    public SignUp() {
        initComponents();   
        
        btnSignUp.addActionListener(e -> handleSignUp());
        btnLogin.addActionListener(e -> {
            new Login().setVisible(true);
            this.dispose();
        });
    }
    
    private void handleSignUp(){
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
            JOptionPane.showMessageDialog(this, "Account creeated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Registration failed. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("Sign Up");
        jLabel1.setPreferredSize(new java.awt.Dimension(114, 45));

        txtFullName.setBackground(new java.awt.Color(102, 102, 102));
        txtFullName.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        txtFullName.setText("Full Name ");
        txtFullName.setRequestFocusEnabled(false);

        fullName.setForeground(new java.awt.Color(102, 102, 102));

        txtEmail.setBackground(new java.awt.Color(102, 102, 102));
        txtEmail.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        txtEmail.setText("Email");

        passwordField.setForeground(new java.awt.Color(102, 102, 102));
        passwordField.setPreferredSize(new java.awt.Dimension(64, 24));

        email.setForeground(new java.awt.Color(102, 102, 102));
        email.setPreferredSize(new java.awt.Dimension(64, 24));

        txtPassword.setBackground(new java.awt.Color(102, 102, 102));
        txtPassword.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        txtPassword.setText("Password");

        jLabel6.setText("Already have an acoount? ");

        btnLogin.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(153, 0, 0));
        btnLogin.setText("Log In");

        btnSignUp.setBackground(new java.awt.Color(153, 0, 0));
        btnSignUp.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btnSignUp.setForeground(new java.awt.Color(255, 255, 255));
        btnSignUp.setText("Sign Up");

        jLabel5.setText("Welcome to SportEase! Get ready to book your favourite venues with ease!.");

        cmbSecurityQuestion.setBackground(new java.awt.Color(102, 102, 102));
        cmbSecurityQuestion.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        cmbSecurityQuestion.setText("Security Question");

        question.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What is the name of your first pet?", "Which school did you pass SEE?", "What is name of town you were born in?", "What is your nickname?" }));

        txtAnswer.setBackground(new java.awt.Color(102, 102, 102));
        txtAnswer.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        txtAnswer.setText("Answer");

        answer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 97, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPassword)
                        .addComponent(txtEmail)
                        .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(74, 74, 74))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(txtAnswer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(answer))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cmbSecurityQuestion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(question, 0, 274, Short.MAX_VALUE))
                            .addComponent(passwordField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(email, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fullName, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(237, 237, 237))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSecurityQuestion)
                    .addComponent(question, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnswer)
                    .addComponent(answer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(100, 20, 570, 450);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 740, 490);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void answerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_answerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    // End of variables declaration//GEN-END:variables

public void addSignUpListner(ActionListener listener){
    btnSignUp.addActionListener(listener);
}

public void addLoginListner(ActionListener listener){
    btnLogin.addActionListener(listener);
}

public String getFullName(){
    return txtFullName.getText().trim();
}

public String getEmail(){
    return txtEmail.getText().trim();
}

public String getSelectedItem() {
    return question.getSelectedItem().toString();
}

public String getPassword(){
    return new String(passwordField.getPassword());
}

public String getAnswer(){
    return txtAnswer.getText().trim();
}

}
