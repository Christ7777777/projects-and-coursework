package drivewayparking.app;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminControllerTests {

    @LocalServerPort
    int port;

    private final String testEmail = "admin1@drivewayparking.com";

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void test1_getAdminByEmail() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/admins/getAdmin/Email/" + testEmail)
                .then()
                .assertThat().statusCode(200);
    }

}
