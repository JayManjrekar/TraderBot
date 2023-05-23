import java.util.*;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }


class Portfolio {
    private Map<String, Integer> stocks;

    public Portfolio() {
        this.stocks = new HashMap<>();
    }

    public void buyStock(Stock stock, int quantity) {
        int currentQuantity = stocks.getOrDefault(stock.getSymbol(), 0);
        stocks.put(stock.getSymbol(), currentQuantity + quantity);
    }

    public boolean sellStock(String symbol, int quantity) {
        int currentQuantity = stocks.getOrDefault(symbol, 0);
        if (currentQuantity < quantity) {
            return false;
        } else {
            stocks.put(symbol, currentQuantity - quantity);
            return true;
        }
    }

    public void printPortfolio() {
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            System.out.println("Symbol: " + entry.getKey() + ", Quantity: " + entry.getValue());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio();

        while (true) {
            System.out.println("1. Buy Stock");
            System.out.println("2. Sell Stock");
            System.out.println("3. View Portfolio");
            System.out.println("4. Exit");
            System.out.println("Enter choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter stock symbol:");
                    String symbol = scanner.nextLine();
                    System.out.println("Enter stock price:");
                    double price = scanner.nextDouble();
                    System.out.println("Enter quantity:");
                    int quantity = scanner.nextInt();

                    portfolio.buyStock(new Stock(symbol, price), quantity);
                    break;

                case 2:
                    System.out.println("Enter stock symbol to sell:");
                    String sellSymbol = scanner.nextLine();
                    System.out.println("Enter quantity to sell:");
                    int sellQuantity = scanner.nextInt();

                    if (portfolio.sellStock(sellSymbol, sellQuantity)) {
                        System.out.println("Stock Sold Successfully");
                    } else {
                        System.out.println("Not Enough Stock to Sell");
                    }
                    break;

                case 3:
                    portfolio.printPortfolio();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
