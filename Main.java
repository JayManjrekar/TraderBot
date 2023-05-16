public class Main {
    public static void main(String[] args) {
        TraderBot bot = new TraderBot();
        bot.addStock(new Stock("AAPL", 150));
        bot.addStock(new Stock("GOOGL", 1200));

        // Check prices every minute for an hour
        for (int i = 0; i < 60; i++) {
            bot.checkPrices();
            try {
                Thread.sleep(60 * 1000); // Sleep for a minute
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
