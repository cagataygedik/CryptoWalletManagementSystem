import java.sql.Date;

class Transaction {
    private String type; 
    private String cryptocurrencySymbol;
    private double amount;
    private double price;
    private Date timestamp;

    public Transaction(String type, String cryptocurrencySymbol, double amount, double price) {
        this.type = type;
        this.cryptocurrencySymbol = cryptocurrencySymbol;
        this.amount = amount;
        this.price = price;
        this.timestamp = new Date(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Cryptocurrency: " + cryptocurrencySymbol + ", Amount: " + amount + ", Price: $" + price + ", Timestamp: " + timestamp;
    }
}