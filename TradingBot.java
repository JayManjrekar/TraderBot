
import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.domain.alpaca.account.Account;
import net.jacobpeterson.domain.alpaca.asset.Asset;
import net.jacobpeterson.domain.alpaca.order.Order;
import net.jacobpeterson.domain.alpaca.order.OrderSide;
import net.jacobpeterson.domain.alpaca.order.OrderType;
import net.jacobpeterson.domain.alpaca.order.TimeInForce;

public class TradingBot {

    private static final String API_KEY_ID = "<PKC1V2UHIJCY44F40LT7>";
    private static final String API_SECRET = "<95wbv28Nx5hmY9WdAX6d2nfqBvfqrC3MJTi7m3PI>";
    private static final String BASE_API_URL = "https://paper-api.alpaca.markets";
    private static final String SYMBOL = "AAPL";
    private static final double BUY_THRESHOLD = 120.0;
    private static final double SELL_THRESHOLD = 150.0;
    private static final int SLEEP_INTERVAL_MS = 60 * 1000;  // 1 minute

    public static void main(String[] args) {
        AlpacaAPI alpacaAPI = new AlpacaAPI(BASE_API_URL, API_KEY_ID, API_SECRET);
        try {
            while (true) {
                Asset asset = alpacaAPI.getAsset(SYMBOL);
                if (asset.isTradable()) {
                    double lastTradePrice = alpacaAPI.getLastTrade(SYMBOL).getPrice();
                    Account account = alpacaAPI.getAccount();

                    if (lastTradePrice < BUY_THRESHOLD) {
                        // Place a buy order for 1 share if we have enough buying power
                        double buyingPower = Double.parseDouble(account.getBuyingPower());
                        if (buyingPower >= lastTradePrice) {
                            Order buyOrder = new Order(SYMBOL, 1, OrderSide.BUY, OrderType.MARKET, TimeInForce.DAY);
                            alpacaAPI.requestNewOrder(buyOrder);
                            System.out.println("Placed a buy order at price: " + lastTradePrice);
                        }
                    } else if (lastTradePrice > SELL_THRESHOLD) {
                        // Place a sell order for 1 share if we own the stock
                        if (Double.parseDouble(asset.getQty()) > 0) {
                            Order sellOrder = new Order(SYMBOL, 1, OrderSide.SELL, OrderType.MARKET, TimeInForce.DAY);
                            alpacaAPI.requestNewOrder(sellOrder);
                            System.out.println("Placed a sell order at price: " + lastTradePrice);
                        }
                    }

                    // Print the updated account value
                    account = alpacaAPI.getAccount();
                    System.out.println("Updated portfolio value: " + account.getPortfolioValue());
                }
                // Sleep for a while before the next iteration
                Thread.sleep(SLEEP_INTERVAL_MS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
