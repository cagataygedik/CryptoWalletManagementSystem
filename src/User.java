class User {
    private String username;
    private String password;
    private BST<Cryptocurrency> wallet;
    private Stack<Transaction> transactionHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.wallet = new BST<>();
        this.transactionHistory = new Stack<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void buyCryptocurrency(String symbol, double amount) {
        Cryptocurrency crypto = wallet.search(new Cryptocurrency(symbol, 0));
        if (crypto != null) {
            crypto.setAmount(crypto.getAmount() + amount);
        } else {
            wallet.insert(new Cryptocurrency(symbol, amount));
        }
        transactionHistory.push(new Transaction("Buy", symbol, amount));
    }

    public void sellCryptocurrency(String symbol, double amount) {
        Cryptocurrency crypto = wallet.search(new Cryptocurrency(symbol, 0));
        if (crypto != null && crypto.getAmount() >= amount) {
            crypto.setAmount(crypto.getAmount() - amount);
            transactionHistory.push(new Transaction("Sell", symbol, amount));
        } else {
            System.out.println("Insufficient balance or cryptocurrency not found.");
        }
    }

    public void displayWallet() {
        if (wallet.isEmpty()) {
            System.out.println("Wallet is empty.");
        } else {
            System.out.println("Wallet contents:");
            wallet.inorder(); // Display BST contents
        }
    }

    public void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("Transaction history is empty.");
        } else {
            System.out.println("Transaction history:");
            Stack<Transaction> tempStack = new Stack<>(); 
            while (!transactionHistory.isEmpty()) {
                Transaction transaction = transactionHistory.pop();
                System.out.println(transaction);
                tempStack.push(transaction); 
            }
            while (!tempStack.isEmpty()) {
                transactionHistory.push(tempStack.pop());
            }
        }
    }
}