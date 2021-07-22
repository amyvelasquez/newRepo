package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps{

    static String token;

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification generateTokenRequest = given().header("Constant-type", "application/json")
                .body("{\n"
                        + "  \"email\": \"velasquezamy213@gmail.com\", \n"
                        + "  \"password\": \"pw123\"\n"
                        + "}");
        Response generateTokenResponse = generateTokenRequest.when().post(APIConstants.GENERATE_TOKEN_URI);
        generateTokenResponse.prettyPrint();
        token = "Bearer " + generateTokenResponse.jsonPath().getString("token");
    }

}
