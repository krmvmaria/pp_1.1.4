package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String dbURL = "jdbc:mysql://localhost:3306/users";
    public static String dbUserName = "root";
    public static String dbPassword = "12345";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
