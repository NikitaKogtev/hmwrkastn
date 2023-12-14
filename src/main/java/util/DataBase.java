package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    public static final String URL = "jdbc:postgresql://localhost:5432/univer";
    public static final String USER = "postgres";
    public static final String PASSWORD = "qq123tt";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}
