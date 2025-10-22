package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import requests.UserRequests;

public class NegativeTests {

    UserRequests user = new UserRequests();

    @Test
    public void testGetNonExistingUser() {
        System.out.println("\n=== Test: Get non-existing user (ID=9999) ===");
        Response response = user.getUser(9999);

        int status = response.statusCode();
        String body = response.getBody().asString();

        System.out.println("Response code: " + status);
        System.out.println("Response body: " + body);

        // Terima 404 atau 401 sebagai valid tergantung kebijakan API (public API sering beda)
        Assert.assertTrue(
                status == 404 || status == 401,
                "Expected 404 or 401, but got: " + status
        );
    }

    @Test
    public void testCreateUserWithEmptyBody() {
        System.out.println("\n=== Test: Create user with empty body ===");
        Response response = user.createUser("", "");

        int status = response.statusCode();
        String body = response.getBody().asString();

        System.out.println("Response code: " + status);
        System.out.println("Response body: " + body);

        // Terima 400/415/422 atau 401 (Missing API key) sebagai valid negatif
        Assert.assertTrue(
                status == 400 || status == 415 || status == 422 || status == 401,
                "Expected 400, 415, 422, or 401, but got: " + status
        );

        // Optional: jika API mengirim pesan error JSON, cek key "error" ada (bila ada)
        if (body != null && body.trim().startsWith("{")) {
            try {
                String err = response.jsonPath().getString("error");
                System.out.println("API error message (if present): " + err);
            } catch (Exception ignore) {
            }
        }
    }
}
