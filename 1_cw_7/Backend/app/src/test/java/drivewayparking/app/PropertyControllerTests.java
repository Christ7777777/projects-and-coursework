package drivewayparking.app;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PropertyControllerTests {

    @LocalServerPort
    int port;

    private final String testTitle = "testTitle";
    private final Long testUserId = Long.valueOf(1);
    private final String testStreet = "testStreet";
    private final String testCity = "testCity";
    private final String testState = "testState";
    private final Integer testZipcode = 00000;
    private final String testCountry = "testCountry";
    private Double testLatitude = 1.0;
    private Double testLongitude = 1.0;
    private String testDescription = "testDescription";
    private Double testRatePerHour = 1.0;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void test1_getProperties() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/properties/getProperties")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void test2_createProperty() {

        JSONObject newProperty = new JSONObject();
        newProperty.put("title", testTitle);
        newProperty.put("userId", testUserId);
        newProperty.put("street", testStreet);
        newProperty.put("city", testCity);
        newProperty.put("state", testState);
        newProperty.put("zipcode", testZipcode);
        newProperty.put("country", testCountry);
        newProperty.put("latitude", testLatitude);
        newProperty.put("longitude", testLongitude);
        newProperty.put("description", testDescription);
        newProperty.put("ratePerHour", testRatePerHour);

        given()
                .contentType(ContentType.JSON).body(newProperty.toString())
                .when()
                .post("/properties/")
                .then()
                .assertThat().statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/properties/getProperty/Title/" + testTitle)
                .then()
                .assertThat().statusCode(200)
                .body("title", equalTo(testTitle))
                .body("street", equalTo(testStreet))
                .body("city", equalTo(testCity))
                .body("state", equalTo(testState))
                .body("zipcode", equalTo(testZipcode))
                .body("country", equalTo(testCountry))
                .body("zipcode", equalTo(testZipcode))
                .body("description", equalTo(testDescription));
    }

    @Test
    public void test3_deleteProperty() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/properties/getProperty/Title/" + testTitle)
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        JsonPath jsonPathEvaluator = response.jsonPath();
        Integer propertyId = jsonPathEvaluator.get("id");

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/properties/" + propertyId)
                .then()
                .assertThat().statusCode(200);
    }

}
