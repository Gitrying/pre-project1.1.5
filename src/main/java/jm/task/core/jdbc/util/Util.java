package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL="jdbc:mysql://localhost:3306/test";
    private static final String DB_DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String USER_NAME="root";
    private static final String PASSWORD="root";

    public static Connection getConnection() {
        Connection connection=null;
        try{
            Class.forName(DB_DRIVER);
            connection= DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
