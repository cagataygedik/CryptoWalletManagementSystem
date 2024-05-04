import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CryptoPriceChecker {

    public String getCurrentPrice(String symbol) {
        try {
            
            URL url = new URL("https://api.coincap.io/v2/assets?limit=1&search=" + symbol);

          
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            
            Scanner responseReader = new Scanner(conn.getInputStream());
            StringBuilder responseBuilder = new StringBuilder();
            while (responseReader.hasNextLine()) {
                responseBuilder.append(responseReader.nextLine());
            }
            responseReader.close();

          
            String response = responseBuilder.toString();
            return extractPrice(response);

        } catch (Exception e) {
            System.out.println("Failed to retrieve price information.");
            e.printStackTrace();
            return "Price retrieval failed";
        }
    }

    private String extractPrice(String response) {
        
        int priceIndex = response.indexOf("\"priceUsd\":\"");
        if (priceIndex != -1) {
            int startIndex = priceIndex + "\"priceUsd\":\"".length();
            int endIndex = response.indexOf("\"", startIndex);
            if (endIndex != -1) {
                return response.substring(startIndex, endIndex);
            }
        }
        return "Price not found";
    }
}
