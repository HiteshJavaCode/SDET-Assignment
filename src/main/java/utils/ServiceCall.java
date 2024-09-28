package utils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ServiceCall {
    private final String BASE_URL = "http://jsonplaceholder.typicode.com";
    public CloseableHttpResponse get(String endpoint) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + endpoint);
        return httpClient.execute(httpGet);
    }
}
