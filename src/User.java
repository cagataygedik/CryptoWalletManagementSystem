class User {
    private String username;
    private String password;
    private BST<Cryptocurrency> wallet;
    private Stack<Transaction> transactionHistory;
    private CryptoPriceChecker priceChecker;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.wallet = new BST<>();
        this.transactionHistory = new Stack<>();
        this.priceChecker = new CryptoPriceChecker();
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void buyCryptocurrency(String symbol, double amount) {
        double price = getPrice(symbol); 
        if (price == -1) {
            System.out.println("Failed to retrieve price information for " + symbol);
            return;
        }

        Cryptocurrency crypto = wallet.search(new Cryptocurrency(symbol, 0, 0));
        if (crypto != null) {
            crypto.setAmount(crypto.getAmount() + amount);
        } else {
            wallet.insert(new Cryptocurrency(symbol, amount, price));
        }
        transactionHistory.push(new Transaction("Buy", symbol, amount, price));
    }

    public void sellCryptocurrency(String symbol, double amount) {
        double price = getPrice(symbol);
        if (price == -1) {
            System.out.println("Failed to retrieve price information for " + symbol);
            return;
        }

        Cryptocurrency crypto = wallet.search(new Cryptocurrency(symbol, 0, 0));
        if (crypto != null && crypto.getAmount() >= amount) {
            crypto.setAmount(crypto.getAmount() - amount);
            transactionHistory.push(new Transaction("Sell", symbol, amount, price));
        } else {
            System.out.println("Insufficient balance or cryptocurrency not found.");
        }
    }

    private double getPrice(String symbol) {
        String priceStr = priceChecker.getCurrentPrice(symbol);
        try {
            return Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            System.out.println("Failed to parse price for " + symbol);
            return -1;
        }
    }

    public void displayWallet() {
        if (wallet.getRoot() == null || wallet.isEmpty()) {
            System.out.println("Wallet is empty.");
        } else {
            System.out.println("Wallet contents:");
            displayNonZeroCrypto(wallet.getRoot());
        }
    }

    private void displayNonZeroCrypto(BSTNode<Cryptocurrency> node) {
        if (node != null) {
            displayNonZeroCrypto(node.left);
            Cryptocurrency crypto = node.data;
            if (crypto.getAmount() > 0) {
                System.out.println(crypto);
            }
            displayNonZeroCrypto(node.right);
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