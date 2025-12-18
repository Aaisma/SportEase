package controller;

import dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.UserModel;
import view.SignUp;

public class UserController {
    
    private final UserDao userdao = new UserDao();
    private final SignUp userView;

    public UserController(SignUp userView) {
        this.userView = userView;
        
        userView.AddUserListner(new SignUpListener());
        
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class SignUpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String username = userView.getUsernameField().getText();
                String email = userView.getEmailField().getText();
                String password = new String(userView.getPasswordField().getText());

                UserModel usermodel = new UserModel(username, email, password);

                boolean check = userdao.check(usermodel);

                if (check) {
                    JOptionPane.showMessageDialog(userView, "Duplicated");
                } else {
                    userdao.signUp(usermodel);  
                    JOptionPane.showMessageDialog(userView, "Successful");
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
