import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserManager userManager = new UserManager(scanner);
    private static CryptoPriceChecker priceChecker = new CryptoPriceChecker(); 

    public static void main(String[] args) {
        userManager.loadUsers();
        boolean exit = false;
        while (!exit) {
            printMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    userManager.register();
                    break;
                case 2:
                    userManager.login();
                    break;
                case 3:
                     userManager.checkCryptoPrice(priceChecker);
                    break;
                case 4:
                    userManager.saveUsers();
                    exit = true;
                    break;
                default:
                    System.out.println("\u001B[31mInvalid option. Please try again.\u001B[0m");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\nWelcome to Cryptocurrency Wallet Management System");
        System.out.println("1. Register - Create a new account");
        System.out.println("2. Login - Access your account");
        System.out.println("3. Check Cryptocurrency Price");
        System.out.println("4. Exit - Close the application");
        System.out.print("Choose an option: ");
    }

    
}
