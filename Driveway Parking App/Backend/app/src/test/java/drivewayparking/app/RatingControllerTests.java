package drivewayparking.app;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RatingControllerTests {

    @LocalServerPort
    int port;

    private final Long id = Long.valueOf(6);

    private ValidatableResponse validatableReponse;
    private ValidatableResponse validatableReponse1;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void test1_getRatings() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("ratings/")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void test2_createRating() {

        JSONObject rating = new JSONObject();
        rating.put("id", 6);
        rating.put("comments", "this is a test rating!");
        rating.put("safety", 5);
        rating.put("accommodation", 5);
        rating.put("responsiveness", 5);

        validatableReponse = given()
                .contentType(ContentType.JSON).body(rating.toString())
                .when()
                .post("ratings/")
                .then()
                .assertThat().statusCode(200);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("ratings/" + id)
                .then()
                .extract().response();

        Assertions.assertEquals(6, response.jsonPath().getInt("id"));
        Assertions.assertEquals(5.0, response.jsonPath().getFloat("accommodation"), 0.1);
        Assertions.assertEquals(5.0, response.jsonPath().getFloat("responsiveness"), 0.1);
        Assertions.assertEquals(5.0, response.jsonPath().getFloat("safety"), 0.1);

//        Assertions.assertEquals("test rating!", response.jsonPath().getString("comments"));
    }

    @Test
    public void test3_getRatingById() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("ratings/" + id)
                .then()
                .extract().response();

        Assertions.assertEquals(6, response.jsonPath().getInt("id"));
        Assertions.assertEquals(5.0, response.jsonPath().getFloat("accommodation"), 0.1);
        Assertions.assertEquals(5.0, response.jsonPath().getFloat("responsiveness"), 0.1);
        Assertions.assertEquals(5.0, response.jsonPath().getFloat("safety"), 0.1);
    }


    @Test
    public void test4_getAllPropertyRatings() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("ratings/property/" + id)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void test5_deleteRating() {

        validatableReponse = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("ratings/" + id)
                .then()
                .assertThat().statusCode(200);
    }

}
