import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class StockAPI {
    private static final String API_URL = "https://api.example.com/stocks";

    public static String getStockData() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStream inputStream = conn.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        StringBuilder response = new StringBuilder();
        while (scanner.hasNextLine()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        return response.toString();
    }

    public static void main(String[] args) {
        try {
            String stockData = getStockData();
            System.out.println(stockData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
