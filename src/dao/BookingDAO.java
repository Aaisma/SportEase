package dao;

import java.sql.Connection;
import util.DBConnection;

public class BookingDAO {

    private Connection con;

    public BookingDAO() {
        con = DBConnection.getConnection();
    }
}
