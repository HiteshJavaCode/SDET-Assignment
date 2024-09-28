package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.ArrayList;
import java.util.List;

public class ServiceConfig {
    private final ServiceCall serviceCall;

    public ServiceConfig() {
        this.serviceCall = new ServiceCall();
    }

    public List<JsonNode> getUsers() throws Exception {
        CloseableHttpResponse response = serviceCall.get("/users");
        ObjectMapper mapper = new ObjectMapper();
        return List.of(mapper.readValue(response.getEntity().getContent(), JsonNode[].class));
    }

    public List<JsonNode> getTodos(int userId) throws Exception {
        CloseableHttpResponse response = serviceCall.get("/todos?userId=" + userId);
        ObjectMapper mapper = new ObjectMapper();
        return List.of(mapper.readValue(response.getEntity().getContent(), JsonNode[].class));
    }
}
