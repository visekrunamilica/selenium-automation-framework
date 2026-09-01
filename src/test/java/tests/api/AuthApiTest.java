package tests.api;

import api.AuthClient;
import base.BaseApiTest;
import config.ConfigReader;
import io.restassured.response.Response;
import models.AuthRequest;
import models.AuthResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthApiTest extends BaseApiTest {
    @Test
    public void userCanAuthenticate() {

        AuthRequest authRequest = new AuthRequest(
                ConfigReader.get("apiUsername"),
                ConfigReader.get("apiPassword")
        );

        AuthClient authClient = new AuthClient();

        Response response = authClient.authenticate(authRequest);

        Assert.assertEquals(response.getStatusCode(), 200);

        AuthResponse authResponse = response.as(AuthResponse.class);

        Assert.assertNotNull(authResponse.getToken());
        Assert.assertFalse(authResponse.getToken().isBlank());
    }
}
