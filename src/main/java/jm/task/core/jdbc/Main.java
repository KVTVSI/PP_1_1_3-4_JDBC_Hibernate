package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Xinia", "Lubimova", (byte) 21);
        userService.saveUser("Danil", "Lubimov", (byte) 22);
        userService.saveUser("Evgeniy", "Kafanov", (byte) 22);
        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
