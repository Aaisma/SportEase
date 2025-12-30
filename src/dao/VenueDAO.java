package dao;

import java.sql.Connection;
import util.DBConnection;

public class VenueDAO {

    private Connection con;

    public VenueDAO() {
        con = DBConnection.getConnection();
    }
}

