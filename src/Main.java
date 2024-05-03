import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        URI uri = URI.create("https://dummyjson.com/products/1");

        HttpRequest request = HttpRequest
            .newBuilder()
            .uri(uri)
            .GET()
            .header("accept", "application/json")
            .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        Gson g = new Gson();
        ProductDTO product = g.fromJson(response.body(), ProductDTO.class);

        System.out.println("Id: " + product.id);
        System.out.println("Title: " + product.title);
        System.out.println("Description: " + product.description);
        System.out.println("Price: " + product.price);
        System.out.println("Discount Percentage: " + product.discountPercentage);
        System.out.println("Rating: " + product.rating);
        System.out.println("Stock: " + product.stock);
        System.out.println("Brand: " + product.brand);
        System.out.println("Category: " + product.category);
        System.out.println("Thumbnail: " + product.thumbnail);
        System.out.println("Images: " + product.images);

        URI uri2 = URI.create("https://dummyjson.com/carts/1");

        HttpRequest request2 = HttpRequest
            .newBuilder()
            .uri(uri2)
            .GET()
            .header("accept", "application/json")
            .build();

        HttpResponse<String> response2 = client.send(
            request2,
            HttpResponse.BodyHandlers.ofString()
        );

        CartDTO cart = g.fromJson(response2.body(), CartDTO.class);

        System.out.println("\nCart:");
        System.out.println("Id: " + cart.id);
        System.out.println("Products: ");
        for( CartDTO.CartProductDTO cartProduct: cart.products) {
            System.out.println("\tId: " + cartProduct.id);
            System.out.println("\tTitle: " + cartProduct.title);
            System.out.println("\tPrice: " + cartProduct.price);
            System.out.println("\tQuantity: " + cartProduct.quantity);
            System.out.println("\tTotal: " + cartProduct.total);
            System.out.println("\tDiscount Percentage: " + cartProduct.discountPercentage);
            System.out.println("\tDiscounted Price: " + cartProduct.discountedPrice);
        }
        System.out.println("Total: " + cart.total);
        System.out.println("Discounted Total: " + cart.discountedTotal);
        System.out.println("User Id: " + cart.userId);
        System.out.println("Total Products: " + cart.totalProducts);
        System.out.println("Total Quantity: " + cart.totalQuantity);

        // Making an asynchronous request

        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(
            request,
            HttpResponse.BodyHandlers.ofString()
        );
        // We can do other things here while the request is in-flight
        System.out.println("Doing other things while the request is in-flight");

        // This blocks until the request is complete
        HttpResponse<String> response3 = responseFuture.get();

        ProductDTO product2 = g.fromJson(response3.body(), ProductDTO.class);

        System.out.println("Last example (async):");
        System.out.println("Id: " + product2.id);
        System.out.println("Title: " + product2.title);
    }
}