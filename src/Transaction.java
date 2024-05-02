import java.sql.Date;

class Transaction {
    private String type; 
    private String cryptocurrencySymbol;
    private double amount;
    private Date timestamp;

    public Transaction(String type, String cryptocurrencySymbol, double amount) {
        this.type = type;
        this.cryptocurrencySymbol = cryptocurrencySymbol;
        this.amount = amount;
        this.timestamp = new Date(0);
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Cryptocurrency: " + cryptocurrencySymbol + ", Amount: " + amount + ", Timestamp: " + timestamp;
    }
}