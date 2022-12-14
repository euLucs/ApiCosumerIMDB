import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

public class IMDBAPIConsume {
    public static void main(String[] args) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/k_mscclnj7"))
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.of(20,SECONDS))
                .GET()
                .build();

        String jsonDividido = HttpClient.newBuilder()
                .build()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        String replaceString = jsonDividido.replaceAll("\\[|\\]", "");
        String moreReplaced = replaceString.replaceAll("\\{\"items\":|,\"errorMessage\":\"\"}", "");

        //System.out.println(moreReplaced);

        Arrays.stream(moreReplaced.split("[{}]")).toList().forEach(System.out::println);


    }
}