import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;

public class UserManager {
    private Scanner scanner;
    private Map<String, User> users = new HashMap<>();
    private static final String USERS_FILE = "bin/users.json";
    private Gson gson = new Gson();
    public UserManager(Scanner scanner) {
        this.scanner = scanner;
    }


    public void register() {
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

    public void login() {
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

    public void handleUser(User user) {
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

    public void buyCryptocurrency(User user) {
        System.out.println("\n\u001B[34mBuy Cryptocurrency\u001B[0m");
        System.out.print("Enter cryptocurrency symbol: ");
        String symbol = scanner.nextLine();
        System.out.print("Enter amount to buy: ");
        double amount = scanner.nextDouble();
        user.buyCryptocurrency(symbol, amount);
        System.out.println("\u001B[32mTransaction completed.\u001B[0m");
    }


    public void sellCryptocurrency(User user) {
        System.out.println("\n\u001B[34mSell Cryptocurrency\u001B[0m");
        System.out.print("Enter cryptocurrency symbol: ");
        String symbol = scanner.nextLine();
        System.out.print("Enter amount to sell: ");
        double amount = scanner.nextDouble();
        user.sellCryptocurrency(symbol, amount);
        System.out.println("\u001B[32mTransaction completed.\u001B[0m");
    }

    public void loadUsers() {
        try (FileReader fileReader = new FileReader(USERS_FILE)) {
            Type userMapType = new TypeToken<Map<String, User>>() {}.getType();
            users = gson.fromJson(fileReader, userMapType);
            if (users == null) {
                users = new HashMap<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No users found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading user data.");
            e.printStackTrace();
        }
    }

    public void saveUsers() {
        try (FileWriter fileWriter = new FileWriter(USERS_FILE)) {
            gson.toJson(users, fileWriter);
        } catch (IOException e) {
            System.out.println("An error occurred while saving user data.");
            e.printStackTrace();
        }
    }
    public void checkCryptoPrice(CryptoPriceChecker priceChecker) {
        System.out.print("\n\u001B[34mCheck Cryptocurrency Price\u001B[0m");
        System.out.print("\nEnter cryptocurrency symbol: ");
        String symbol = scanner.nextLine();
        String price = priceChecker.getCurrentPrice(symbol);
        System.out.println("The current price of " + symbol + " is " + price);
    }

}

