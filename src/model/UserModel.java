package model;

public class UserModel {
    private int user_id;
    private String username;
    private String password;
    private String email;

    // Default constructor (optional but recommended)
    public UserModel() {}

    // Parameterized constructor
    public UserModel(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Optional: for debugging
    @Override
    public String toString() {
        return "UserModel{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
