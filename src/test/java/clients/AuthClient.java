package clients;

import config.RequestSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthClient {

    public Response generateToken(String body) {
        return given()
                .relaxedHTTPSValidation()
                .spec(RequestSpec.getRequestSpec())
                .body(body)
                .log().all()
                .when()
                .post("/auth")
                .then()
                .log().all()
                .extract()
                .response();
    }
}