package restassured;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static utils.Constants.*;

public class DeserializationWithRestAssured {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet/";

    }

    @Test
    public void test1() {
        File petJson = new File("pet.json");
        RestAssured.given().header(ACCEPT, APPLICATION_JSON)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body(petJson)
                .when().put()
                .then().assertThat().statusCode(200)
                .and().assertThat().body("name", Matchers.equalTo("hatiko"))
                .and().assertThat().body("status", Matchers.is("waiting"))
                .body("category.name", Matchers.is("string"));



    }

}
