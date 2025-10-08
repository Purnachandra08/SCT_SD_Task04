import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;

public class ProductScraper {
    public static void main(String[] args) {
        // Website URL for scraping
        String url = "https://webscraper.io/test-sites/e-commerce/allinone/computers/laptops";
        // Output CSV file
        String csvFile = "products.csv";

        try (FileWriter writer = new FileWriter(csvFile)) {
            // Write CSV header
            writer.append("Product Name,Price,Rating\n");

            // Fetch and parse the HTML document
            Document doc = Jsoup.connect(url).get();

            // Select product elements
            Elements products = doc.select(".thumbnail");

            // Loop through each product and extract details
            for (Element product : products) {
                String name = product.select(".title").attr("title");
                String price = product.select(".price").text();
                String rating = product.select(".ratings .rating-stars").attr("data-rating");

                // Write to CSV
                writer.append(String.format("%s,%s,%s\n", name, price, rating));
            }

            System.out.println("✅ Data scraped and saved to " + csvFile);

        } catch (IOException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
