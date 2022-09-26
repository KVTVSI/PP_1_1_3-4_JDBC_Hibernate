package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pp_1_1_3?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String ROOT = "root";
    private static final String PASSWORD = "admin";

    public Connection connectionBD() {
        try  {
            Connection connection = DriverManager.getConnection(URL, ROOT, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Подключение к БД выполнено");
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
