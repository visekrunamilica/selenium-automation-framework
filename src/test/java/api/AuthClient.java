package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AuthRequest;

import static io.restassured.RestAssured.given;

public class AuthClient {

    public Response authenticate(AuthRequest authRequest) {
        return given()
                .contentType(ContentType.JSON)
                .body(authRequest)
                .when()
                .post("/auth");
    }
}
