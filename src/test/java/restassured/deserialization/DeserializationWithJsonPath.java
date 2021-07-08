package restassured.deserialization;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static utils.Constants.ACCEPT;
import static utils.Constants.APPLICATION_JSON;

public class DeserializationWithJsonPath {


    @Test
    public void test1() {

        RestAssured.baseURI = "https://swapi.dev";
        RestAssured.basePath = "api/vehicles/";

        //https://swapi.dev/api/vehicles/?page=2
        Response response = given().header(ACCEPT, APPLICATION_JSON)
                .param("page", "2")
                .when().get()
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType(ContentType.JSON)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> resultsList = jsonPath.getList("results");
        for (Map<String, Object> result : resultsList){
            System.out.println(result.get("name"));

        }

//        Map<String, Object> results1stElement = jsonPath.getMap("results[0]");
//        jsonPath.getInt("");
//        jsonPath.getBoolean("");



    }
}
