import java.util.ArrayList;

public class TraderBot {
    private ArrayList<Stock> portfolio;
    private final double SELL_PERCENTAGE = 1.10; // Sell when price is 110% of buy price
    private final double BUY_PERCENTAGE = 0.90; // Buy when price is 90% of last check

    public TraderBot() {
        this.portfolio = new ArrayList<Stock>();
    }

    public void addStock(Stock stock) {
        this.portfolio.add(stock);
    }

    public void checkPrices() {
        for (Stock stock : portfolio) {
            // Get the current price from some external source
            double newPrice = getCurrentPriceFromAPI(stock.getSymbol());

            // Update the current price
            stock.setCurrentPrice(newPrice);

            // If we own the stock and the price has gone up enough, sell it
            if (stock.getBuyPrice() > 0 && newPrice >= stock.getBuyPrice() * SELL_PERCENTAGE) {
                System.out.println("Sold " + stock.getSymbol() + " at " + newPrice);
                stock.setBuyPrice(0);
            }
            // If we don't own the stock and the price has gone down enough, buy it
            else if (stock.getBuyPrice() == 0 && newPrice <= stock.getBuyPrice() * BUY_PERCENTAGE) {
                System.out.println("Bought " + stock.getSymbol() + " at " + newPrice);
                stock.setBuyPrice(newPrice);
            }
        }
    }

    private double getCurrentPriceFromAPI(String symbol) {
        // This would normally call out to an API and return the price.
        // For the sake of simplicity, we'll just simulate price changes by returning a random number.
        return Math.random() * 100;
    }
}
