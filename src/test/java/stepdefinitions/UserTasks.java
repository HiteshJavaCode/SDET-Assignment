package stepdefinitions;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.ServiceConfig;
import static org.junit.Assert.assertTrue;
import java.util.List;

public class UserTasks {
    private ServiceConfig serviceConfig = new ServiceConfig();
    private List<JsonNode> users;
    private int totalTodos;
    private int completedTodos;

    @Given("User has the todo tasks")
    public void userHasTheTodoTasks() throws Exception {
        users = serviceConfig.getUsers();
    }
    @Given("User belongs to the city FanCode")
    public void userBelongsToTheCityFanCode() throws Exception {
        for (JsonNode user : users) {
            double lat = user.get("address").get("geo").get("lat").asDouble();
            double lng = user.get("address").get("geo").get("lng").asDouble();
            if (lat >= -40 && lat <= 5 && lng >= 5 && lng <= 100) {
                List<JsonNode> todos = serviceConfig.getTodos(user.get("id").asInt());
                totalTodos += todos.size();
                completedTodos += (int) todos.stream().filter(todo -> todo.get("completed").asBoolean()).count();
            }
        }
    }
    @Then("User Completed task percentage should be greater than 50%")
    public void userCompletedTaskPercentageShouldBeGreaterThanFifty() {
        if (totalTodos > 0) {
            double percentage = (double) completedTodos / totalTodos * 100;
            assertTrue("Completed task percentage should be greater than 50%", percentage > 50);
        } else {
            assertTrue("No todo tasks available for user", false);
        }
    }
}
