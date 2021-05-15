package service;

import model.User;

import java.util.Scanner;

import static service.Helper.MD5;
import static service.Helper.makeUsers;

public class LoginService {
    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username.");
        String username = scanner.next();
        System.out.println("Enter your password.");
        String password = scanner.next();
        User user = searchUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("There is no such user");
        } else if (user.getPassword().equals(MD5(password))) {
            System.out.println("Welcome " + user.getFullName());
        } else {
            throw new RuntimeException("You entered the wrong password.");
        }
    }

    private User searchUserByUsername(String username) {
        String[] userStrings = FileService.readLines("Users\\user.txt");
        User[] users = makeUsers(userStrings);
        User u1 = new User();
        u1 = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                u1 = user;
            }
        }
        return u1;
    }
}
