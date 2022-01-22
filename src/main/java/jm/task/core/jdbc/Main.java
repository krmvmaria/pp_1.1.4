package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Oscar", "Williams", (byte) 25);
        user.saveUser("Harry", "Martin", (byte) 45);
        user.saveUser("Lana", "Peters", (byte) 31);
        System.out.println(user.getAllUsers());
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
