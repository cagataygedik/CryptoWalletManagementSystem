import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome to Cryptocurrency Wallet Management System");
            System.out.println("1. Register - Create a new account");
            System.out.println("2. Login - Access your account");
            System.out.println("3. Exit - Close the application");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("\u001B[31mInvalid option. Please try again.\u001B[0m");
            }
        }
    }

    private static void register() {
        System.out.println("\n\u001B[34mRegistration\u001B[0m");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("\u001B[31mUsername already exists. Please choose a different username.\u001B[0m");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.put(username, new User(username, password));
        System.out.println("\u001B[32mRegistration successful. You can now login to your account.\u001B[0m");
    }

    private static void login() {
        System.out.println("\n\u001B[34mLogin\u001B[0m");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = users.get(username);
        if (user != null && user.authenticate(password)) {
            System.out.println("\u001B[32mLogin successful. Welcome, " + username + "!\u001B[0m");
            handleUser(user);
        } else {
            System.out.println("\u001B[31mInvalid username or password. Please try again.\u001B[0m");
        }
    }

    private static void handleUser(User user) {
        boolean logout = false;
        while (!logout) {
            System.out.println("\n\u001B[34mMenu\u001B[0m");
            System.out.println("1. Buy cryptocurrency");
            System.out.println("2. Sell cryptocurrency");
            System.out.println("3. Display wallet");
            System.out.println("4. Display transaction history");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    buyCryptocurrency(user);
                    break;
                case 2:
                    sellCryptocurrency(user);
                    break;
                case 3:
                    user.displayWallet();
                    break;
                case 4:
                    user.displayTransactionHistory();
                    break;
                case 5:
                    logout = true;
                    System.out.println("\u001B[32mLogout successful.\u001B[0m");
                    break;
                default:
                    System.out.println("\u001B[31mInvalid option. Please try again.\u001B[0m");
            }
        }
    }

    private static void buyCryptocurrency(User user) {
        System.out.println("\n\u001B[34mBuy Cryptocurrency\u001B[0m");
        System.out.print("Enter cryptocurrency symbol: ");
        String symbol = scanner.nextLine();
        System.out.print("Enter amount to buy: ");
        double amount = scanner.nextDouble();
        user.buyCryptocurrency(symbol, amount);
        System.out.println("\u001B[32mTransaction completed.\u001B[0m");
    }


    private static void sellCryptocurrency(User user) {
        System.out.println("\n\u001B[34mSell Cryptocurrency\u001B[0m");
        System.out.print("Enter cryptocurrency symbol: ");
        String symbol = scanner.nextLine();
        System.out.print("Enter amount to sell: ");
        double amount = scanner.nextDouble();
        user.sellCryptocurrency(symbol, amount);
        System.out.println("\u001B[32mTransaction completed.\u001B[0m");
    }
}