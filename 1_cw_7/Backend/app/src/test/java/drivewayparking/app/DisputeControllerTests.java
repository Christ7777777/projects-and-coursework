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

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DisputeControllerTests {

    @LocalServerPort
    int port;

    private final Long testBookingId = Long.valueOf(1);
    private final String testMessage = "test message";

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void test1_getDisputes() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/disputes/getDisputes")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void test2_createDispute() {

        JSONObject newDispute = new JSONObject();
        newDispute.put("bookingId", testBookingId);
        newDispute.put("message", testMessage);

        given()
                .contentType(ContentType.JSON).body(newDispute.toString())
                .when()
                .post("/disputes")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void test3_resolveDispute() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/disputes/getDispute/BookingId/" + testBookingId)
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        JsonPath jsonPathEvaluator = response.jsonPath();
        Integer disputeId = jsonPathEvaluator.get("id");

        given()
                .contentType(ContentType.JSON)
                .when()
                .put("/disputes/resolveDispute/" + disputeId)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void test4_deleteDispute() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/disputes/getDispute/BookingId/" + testBookingId)
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        JsonPath jsonPathEvaluator = response.jsonPath();
        Integer disputeId = jsonPathEvaluator.get("id");

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/disputes/deleteDispute/" + disputeId)
                .then()
                .assertThat().statusCode(200);
    }

}
