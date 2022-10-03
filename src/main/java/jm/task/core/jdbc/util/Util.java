package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pp_1_1_3?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String ROOT = "root";
    private static final String PASSWORD = "admin";
    private static SessionFactory sessionFactory;

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

    public static SessionFactory getSessionFactory() {
        try {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/pp_1_1_3");
            properties.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
            properties.setProperty("hibernate.connection.username","root");
            properties.setProperty("hibernate.connection.password","admin");
            properties.setProperty("hibernate.id.new_generator_mappings","admin");
            properties.setProperty("current_session_context_class","thread");
            properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
            properties.setProperty("show_sql","true");
            return sessionFactory = new Configuration().addProperties(properties).addAnnotatedClass(User.class).buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
