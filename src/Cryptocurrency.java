class Cryptocurrency implements Comparable<Cryptocurrency> {
    private String symbol;
    private double amount;

    public Cryptocurrency(String symbol, double amount) {
        this.symbol = symbol;
        this.amount = amount;
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

    @Override
    public String toString() {
        return symbol + ": " + amount;
    }

    @Override
    public int compareTo(Cryptocurrency other) {
        return this.symbol.compareTo(other.symbol);
    }
}