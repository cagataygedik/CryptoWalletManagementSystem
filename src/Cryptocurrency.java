class Cryptocurrency implements Comparable<Cryptocurrency> {
    private String symbol;
    private double amount;
    private double price; 

    public Cryptocurrency(String symbol, double amount, double price) {
        this.symbol = symbol;
        this.amount = amount;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return symbol + ": " + amount + " at $" + price + " each";
    }

    @Override
    public int compareTo(Cryptocurrency other) {
        return this.symbol.compareTo(other.symbol);
    }
}
