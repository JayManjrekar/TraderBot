import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class StockOptionController {

    private final StockOptionService stockOptionService;

    @Autowired
    public StockOptionController(StockOptionService stockOptionService) {
        this.stockOptionService = stockOptionService;
    }

    @GetMapping("/options/aapl")
    public String getAAPLStockOptions() throws Exception {
        return stockOptionService.fetchAAPLStockOptions();
    }
}
