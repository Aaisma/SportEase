package dao;

import java.sql.Connection;
import util.DBConnection;

public class UserDAO {

    private Connection con;

    public UserDAO() {
        con = DBConnection.getConnection();
    }
}
