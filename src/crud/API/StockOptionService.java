import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class StockOptionService {

    public String fetchAAPLStockOptions() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://stock-and-options-trading-data-provider.p.rapidapi.com/options/aapl"))
            .header("X-RapidAPI-Proxy-Secret", "a755b180-f5a9-11e9-9f69-7bf51e845926")
            .header("X-RapidAPI-Key", "39c4bf8c2emsh30b02ab6dc01dd9p13f427jsn690a650cf2ec")
            .header("X-RapidAPI-Host", "stock-and-options-trading-data-provider.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        
        return response.body();
    }
}
