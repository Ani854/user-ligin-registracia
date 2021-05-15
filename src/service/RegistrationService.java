package service;

import model.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static service.Helper.MD5;
import static service.Helper.makeUsers;

public class RegistrationService {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public void register() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your  name.");
        String name = scanner.next();
        System.out.println("Enter your surname.");
        String surname = scanner.next();
        String fullName = name + " " + surname;
        if (fullNameValidation(fullName)) {
            user.setFullName(fullName);
        } else throw new RuntimeException("Full name does not support criteria.");

        System.out.println("Enter your username.");
        String username = scanner.next();
        if (usernameValidation(username)) {
            user.setUsername(username);
        } else throw new RuntimeException("Username length is small than 10 or duplicate username.");

        System.out.println("Enter your email.");
        String email = scanner.next();
        if (emailValidation(email)) {
            user.setEmail(email);
        } else throw new RuntimeException("Email does not support criteria");

        System.out.println("Enter your password.");
        String password = scanner.next();
        if (passwordValidation(password)) {

            user.setPassword(MD5(password));
        } else throw new RuntimeException("Password does not support criteria");

        FileService.write("Users\\user.txt", makeUsersToString(user));
    }

    private boolean fullNameValidation(String fullName) {
        return fullName.matches("^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$");
    }

    private boolean usernameValidation(String username) {
        String[] userStrings = FileService.readLines("Users\\user.txt");
        User[] users = makeUsers(userStrings);
        for (int i = 0; i < users.length; i++) {
            if (users[i].getUsername().equals(username)) {
                throw new RuntimeException("Duplicate username ");
            }
        }
        if (username.length() < 8) {
            throw new RuntimeException("Username length is small than 10.");
        }
        return true;
    }

    private boolean emailValidation(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private boolean passwordValidation(String password) {
        return password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!*@#$%^&+=])(?=\\S+$).{8,}");
    }

    private String makeUsersToString(User user) {
        StringBuilder sb = new StringBuilder();
        String str = sb.append(user.getFullName())
                .append(",")
                .append(user.getUsername())
                .append(",")
                .append(user.getEmail())
                .append(",")
                .append(user.getPassword())
                .append("\n")
                .toString();
        return str;
    }
}



