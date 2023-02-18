package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL="jdbc:mysql://localhost:3306/test";
    private static final String DB_DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String USER_NAME="root";
    private static final String PASSWORD="root";

    public static void main(String[] args) throws Exception {

        Class.forName(DB_DRIVER);
        try(Connection conn= DriverManager.getConnection(URL,USER_NAME,PASSWORD); Statement statement=conn.createStatement()){
            System.out.println("Да");
            statement.executeUpdate("CREATE TABLE User(`id` INT PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(20) NOT NULL,`lastName` VARCHAR(20) NOT NULL,`age` INT)");

            //statement.executeUpdate("drop table orders");

        }
    }

    public static Connection getConnection() {
        Connection connection=null;
        try{
            Class.forName(DB_DRIVER);
            connection= DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            System.out.println("OK");
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            System.out.println("ERROR");
        }
        return connection;
    }
}
