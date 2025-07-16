import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.Scanner;
public class stockData {
    static final String API_KEY = "c35e831bc8235ecbbb6906cad78ebeeb";
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in); //creates Scanner input using Scanner class
        while (true) { 
            System.out.println("Enter stock symbol(exit to exit): ");
            String symbol = scanner.nextLine().toUpperCase();// stores input in variable "symbol" as a string in all caps 
            if (symbol.equals("EXIT")) {
                break;
            }
            fetchStockPrice(symbol);// initiates method used to get the stock info using user input variable "symbol"
        }
        scanner.close();
    }
        public static void fetchStockPrice(String symbol) {
            String eodUrl = "http://api.marketstack.com/v1/eod?access_key=" + API_KEY + "&symbols=" + symbol; // url where request is sent; can be found in API documentation
            String tickerUrl = "http://api.marketstack.com/v1/tickers/" + symbol + "?access_key=" + API_KEY;
            try { // starts a try catch to send the request; if it is accpeted, code will run, if not you're cooked
                HttpClient client = HttpClient.newHttpClient(); // creates an HttpClient used to send the request
                HttpRequest eodRequest = HttpRequest.newBuilder() // starts building the request using the url we made previously
                .uri(URI.create(eodUrl)) //uses the url
                .GET() //tells the system we are retrieving information
                .build(); //builds the request
                HttpRequest tickerRequest = HttpRequest.newBuilder() // making a new request for exchange data
                .uri(URI.create(tickerUrl))
                .GET()
                .build();
                HttpResponse<String> response = client.send(eodRequest, HttpResponse.BodyHandlers.ofString()); //sends the request and retrieves data as a string and stores in variable "response"
                HttpResponse<String> response2 = client.send(tickerRequest, HttpResponse.BodyHandlers.ofString()); //sends the request and retrieves data as a string and stores in variable "response2"
                Gson gson = new Gson(); //creates new gson object to parse Json info; we receive the stock data as json, it will be easier to process the data if we use gson
                JsonObject tickerJson = gson.fromJson(response2.body(), JsonObject.class); // converts json string into an object
                JsonObject eodJson = gson.fromJson(response.body(), JsonObject.class); // converts json string into an object; this is so that we can work with the data easily in the code; when we receive the info from the API, we get it as a string and we cant just retrieve the data using get requests; if we convert it to an objects, we can easily extract the data
                JsonObject stockExchange = tickerJson.getAsJsonObject("stock_exchange"); //gets object named "stock_exchange" from the data and stores it in variable named "stockExchange"
                JsonArray dataArray = eodJson.getAsJsonArray("data"); //stock info is stored as array; we do this to get the array from json
                if (dataArray != null && dataArray.size() > 0) { // this makes sure that there is something in the array
                    JsonObject stockInfo = dataArray.get(0).getAsJsonObject(); //uses index value 0 to get the latest info from the data array as a json object so it's easier to work with as said before; stored in variable stockInfo
                    String date = stockInfo.get("date").getAsString(); // from stockInfo, which holds the data from the array, we get the date as a string
                    double close = stockInfo.get("close").getAsDouble();// from stockInfo, which holds the data from the array, we get the closing price as a double(decimal) value
                    System.out.println("Stock: " + symbol); //output info
                    System.out.println("Date: " + date); // output info
                    System.out.println("Closing Price: $" + close); // output info
                }
                else { //if there isn't anything in the array, we send this message
                    System.out.println("No data found for symbol: " + symbol);
                }
                if (stockExchange != null && stockExchange.size() > 0){ // makes sure there is something inside stockExchange
                    String exchangeName = stockExchange.get("acronym").getAsString(); // gets acronym info from stockExchange
                    System.out.println("Exchange: " + exchangeName);
                }
                else {
                    System.out.println("No exchange data found for " + symbol);
                }
            }
            catch(Exception e) { // this ends the try catch method; if there are any errors, it displays the error
                System.out.println("Error retrieving stock data: " + e.getMessage());
            }
        }
}
