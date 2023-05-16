public class Stock {
    private String symbol;
    private double buyPrice;
    private double currentPrice;

    public Stock(String symbol, double currentPrice) {
        this.symbol = symbol;
        this.buyPrice = 0;
        this.currentPrice = currentPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
