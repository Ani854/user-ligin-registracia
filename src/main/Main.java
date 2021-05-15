package main;

import service.FileService;
import service.LoginService;
import service.RegistrationService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileService.createFolder("Users");
        FileService.createFile("Users", "user.txt");
        Scanner scanner = new Scanner(System.in);
        boolean isActive = true;
        while (isActive) {
            System.out.println("Enter command number");
            System.out.println("1.Registration.");
            System.out.println("2.Login.");
            int c = scanner.nextInt();
            boolean active = true;
            switch (c) {
                case 1:
                    while (active) {
                        System.out.println("Enter command number");
                        System.out.println("1.Registration.");
                        System.out.println("2.Back to general menu.");
                        int a = scanner.nextInt();
                        switch (a) {
                            case 1:
                                RegistrationService registrationService = new RegistrationService();
                                registrationService.register();
                                break;
                            case 2:
                                active = false;
                                break;
                            default:
                                System.out.println("Invalid command number");
                                break;
                        }
                    }
                case 2:
                    while (active) {
                        System.out.println("Enter command number");
                        System.out.println("1.Login.");
                        System.out.println("2.Back to general menu.");
                        int a = scanner.nextInt();
                        switch (a) {
                            case 1:
                                LoginService loginService = new LoginService();
                                loginService.login();
                                break;
                            case 2:
                                active = false;
                                break;
                            default:
                                System.out.println("Invalid command number");
                                break;
                        }
                    }
                default:
                    System.out.println("Invalid command number");
                    break;
            }
        }

    }
}
