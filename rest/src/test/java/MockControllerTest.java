import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author: liucaisi
 * @date: 2018/1/11
 */
public class MockControllerTest {
    @Before
    public void before() {
        RestAssured.baseURI = "http://10.21.170.188";
        RestAssured.port = 8416;
    }

    @Test
    public void getStrategyById() {
        ValidatableResponse response = given().param("id", 12).get("/queryStrategyById").then();

        response.body("strategyName", equalTo("主页未提单"));
    }
}
