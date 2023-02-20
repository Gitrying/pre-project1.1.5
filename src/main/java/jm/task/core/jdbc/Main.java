package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService=new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ваня","Златов",(byte)12);
        userService.saveUser("Василиса","Зоева",(byte)5);
        userService.saveUser("Костя","Карасёв",(byte)25);
        userService.saveUser("Ася","Кошкина",(byte)32);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
