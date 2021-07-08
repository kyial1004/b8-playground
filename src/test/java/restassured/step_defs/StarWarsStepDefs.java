package restassured.step_defs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static utils.Constants.ACCEPT;
import static utils.Constants.APPLICATION_JSON;

public class StarWarsStepDefs {

    Response response;

    @Given("I have a valid Star Wars characters endpoint")
    public void i_have_a_valid_star_wars_characters_endpoint() {
        RestAssured.baseURI = "https://swapi.dev";
        RestAssured.basePath = "api/people";
    }

    @When("I send GET request")
    public void i_send_get_request() {
        response = RestAssured.given().header(ACCEPT, APPLICATION_JSON)
                .when().get();
    }

    @Then("I should get {int} status code")
    public void i_should_get_status_code(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);


    }

    @Then("I should response in JSON format")
    public void i_should_response_in_json_format() {

        response.then().assertThat().contentType(APPLICATION_JSON);
    }

    @Then("I should get first Luke Skywalker in the response")
    public void i_should_get_first_luke_skywalker_in_the_response() {
        response.then().assertThat().body("results[0].name", Matchers.equalTo("Luke Skywalker"));

        response.then().body("results.name", Matchers.hasItem("Luke Skywalker"));
    }

}
