package drivewayparking.app;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
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

import java.sql.Timestamp;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookingControllerTests {

    @LocalServerPort
    int port;

    private final Long id = Long.valueOf(4);
    private Response response;
    private ValidatableResponse validatableReponse;


    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void test1_getBookings() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/bookings/")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void test2_getBookingById() {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/bookings/1")
                .then()
                .extract().response();

        Assertions.assertEquals(1, response.jsonPath().getInt("id"));
        Assertions.assertEquals(3, response.jsonPath().getInt("uid"));
        Assertions.assertEquals(6, response.jsonPath().getInt("pid"));
        Assertions.assertEquals("2022-10-10T10:00:00.000+00:00", response.jsonPath().getString("check_in"));
        Assertions.assertEquals("2022-10-12T20:00:00.000+00:00", response.jsonPath().getString("check_out"));
        Assertions.assertEquals(173.42, response.jsonPath().getFloat("cost"), 0.1);
    }

    @Test
    public void test3_getBookingsByProperty() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("bookings/property/" + id)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void test4_getBookingsByUser() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("bookings/user/" + id)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void test5_bookingRequest() {
        JSONObject request = new JSONObject();
        request.put("uid", id);
        request.put("pid", id);
        request.put("check_in", "2021-06-10T10:00:00");
        request.put("check_out", "2021-06-10T12:00:00");

        given()
                .contentType(ContentType.JSON).body(request.toString())
                .when()
                .put("/bookings/request")
                .then()
                .assertThat().statusCode(200);

        response = given()
                .contentType(ContentType.JSON).body(request.toString())
                .when()
                .put("bookings/request")
                .then()
                .extract().response();

        Assertions.assertEquals(4, response.jsonPath().getInt("pid"));
        Assertions.assertEquals("2021-06-10T10:00:00.000+00:00", response.jsonPath().getString("check_in"));
        Assertions.assertEquals("2021-06-10T12:00:00.000+00:00", response.jsonPath().getString("check_out"));
        Assertions.assertEquals(3.98, response.jsonPath().getFloat("cost"), 0.1);
    }

    @Test
    public void test6_addBooking() {
        JSONObject booking = new JSONObject();
        booking.put("uid", id);
        booking.put("pid", id);
        booking.put("check_in", "2023-06-10T10:00:00");
        booking.put("check_out", "2023-06-10T12:00:00");

        validatableReponse = given()
                .contentType(ContentType.JSON).body(booking.toString())
                .when()
                .post("bookings/")
                .then()
                .assertThat().statusCode(200);

//        response = given()
//                .contentType(ContentType.JSON).body(booking.toString())
//                .when()
//                .get("/bookings/10")
//                .then()
//                .extract().response();
//
//        Assertions.assertEquals(4, response.jsonPath().getInt("pid"));
//        Assertions.assertEquals(4, response.jsonPath().getInt("uid"));
//        Assertions.assertEquals("2023-06-10T10:00:00.000+00:00", response.jsonPath().getString("check_in"));
//        Assertions.assertEquals("2023-06-10T12:00:00.000+00:00", response.jsonPath().getString("check_out"));
//        Assertions.assertEquals(3.98, response.jsonPath().getFloat("cost"), 0.1);
    }

    @Test
    public void test6_deleteBooking() {

        validatableReponse = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("bookings/11")
                .then()
                .assertThat().statusCode(200);
    }

}
