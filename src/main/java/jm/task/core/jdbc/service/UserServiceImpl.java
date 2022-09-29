package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    Connection con = new Util().connectionBD();
    public void createUsersTable() {
        try(Statement statement = con.createStatement() ) {
            statement.execute("CREATE TABLE IF NOT EXISTS users(" +
                    "id INT AUTO_INCREMENT," +
                    "name VARCHAR(10)," +
                    "lastName VARCHAR(20)," +
                    "age INT NOT NULL, primary key (id))");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = con.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO users(name,lastName,age) VALUES (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных" );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try(PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try(Statement st = con.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Statement st = con.createStatement()) {
            st.execute("TRUNCATE TABLE users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
