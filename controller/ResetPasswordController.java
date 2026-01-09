package controller;

import javax.swing.JOptionPane;
import view.Login;
import view.ResetPassword;

public class ResetPasswordController {
    private final ResetPassword view;

    public ResetPasswordController(ResetPassword view) {
        this.view = view;
    }

    public void resetPassword(String password, String confirmPassword) {
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill in both fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(view, "Passwords do not match.");
            return;
        }

        String email = ForgetPasswordController.getCurrentEmail();
        // TODO: Update password in database for this email
        JOptionPane.showMessageDialog(view, "Password reset successful!");
        view.dispose();
        new Login().setVisible(true);
    }
}