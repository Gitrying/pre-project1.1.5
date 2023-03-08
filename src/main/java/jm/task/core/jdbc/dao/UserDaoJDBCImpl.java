package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnectionJDBC;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    Connection connection = getConnectionJDBC();

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS User (`id` INT PRIMARY KEY AUTO_INCREMENT,`name` VARCHAR(255), `lastName` VARCHAR(255),`age` INTEGER)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException b) {
                b.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS User";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException b) {
                b.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO User(`name`,`lastName`,`age`)VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException b) {
                b.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM User WHERE `id`=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException b) {
                b.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM User";
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException b) {
                b.printStackTrace();
            }
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM User";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException b) {
                b.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
