package controller;

import javax.swing.JOptionPane;
import dao.userDao;
import view.*;

public class ResetPasswordController {
    private final ResetPassword view;

    public ResetPasswordController(ResetPassword view) {
        this.view = view;
    }

    public void resetPassword(String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(view, "Passwords do not match.");
            return;
        }

        String email = ForgetPasswordController.getCurrentEmail();
        userDao dao = new userDao();
        dao.updatePassword(email, newPassword);

        JOptionPane.showMessageDialog(view, "Password reset successful! Please login.");
        view.dispose();
        new Login().setVisible(true);
    }
}