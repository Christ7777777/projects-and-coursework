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
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTests {

    @LocalServerPort
    int port;

    private final String testEmail = "newUser@restassured.com";
    private final String testPassword = "userPassword";
    private final String testFirstName = "testFirstName";
    private final String testLastName = "testLastName";
    private final String testPhoneNumber = "123456789";
    private final String testGender = "t";
    private final String testEmergencyContact = "testEmergencyContact";

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void test1_getUsers() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/getUsers")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void test2_createUser() {

        JSONObject newUser = new JSONObject();
        newUser.put("email", testEmail);
        newUser.put("password", testPassword);
        newUser.put("firstName", testFirstName);
        newUser.put("lastName", testLastName);
        newUser.put("phoneNumber", testPhoneNumber);

        given()
                .contentType(ContentType.JSON).body(newUser.toString())
                .when()
                .post("/users/")
                .then()
                .assertThat().statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/getUser/Email/" + testEmail)
                .then()
                .assertThat().statusCode(200)
                .body("email", equalTo(testEmail))
                .body("password", equalTo(testPassword))
                .body("firstName", equalTo(testFirstName))
                .body("lastName", equalTo(testLastName))
                .body("phoneNumber", equalTo(testPhoneNumber));
    }

    @Test
    public void test3_updateUser() {

        JSONObject updateUser = new JSONObject();
        updateUser.put("email", testEmail);
        updateUser.put("gender", testGender);
        updateUser.put("emergencyContact", testEmergencyContact);

        given()
                .contentType(ContentType.JSON).body(updateUser.toString())
                .when()
                .put("/users/updateUser")
                .then()
                .assertThat().statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/getUser/Email/" + testEmail)
                .then()
                .assertThat().statusCode(200)
                .body("gender", equalTo(testGender))
                .body("emergencyContact", equalTo(testEmergencyContact));
    }

    @Test
    public void test4_deleteUser() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/getUser/Email/" + testEmail)
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        JsonPath jsonPathEvaluator = response.jsonPath();
        Integer userId = jsonPathEvaluator.get("id");

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/users/" + userId)
                .then()
                .assertThat().statusCode(200);

//        given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get("/users/getUser/Email/" + testEmail)
//                .then()
//                .assertThat().statusCode(200)
//                .body("", equalTo(""));
    }
}
