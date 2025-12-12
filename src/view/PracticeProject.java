
package practice.project;

import view.SignUp;
import controller.UserController;

public class PracticeProject {

   
    public static void main(String[] args) {
     
        SignUp signup = new SignUp();
        UserController user = new UserController(signup);
        user.open();
    }
    
}
