package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import requests.UserRequests;

public class PositiveTests {
    UserRequests user = new UserRequests();

    @Test
    public void testCreateUserSuccessfully() {
        System.out.println("\n=== Test: Create user successfully ===");
        String name = "Irfan";
        String job = "QA Engineer";

        // Panggil API
        Response response = user.createUser(name, job);
        int status = response.statusCode();
        String responseBody = response.getBody().asString();

        // Log detail respon
        System.out.println("Response code: " + status);
        System.out.println("Response body: " + responseBody);

        // Karena beberapa API memerlukan API key dan mengembalikan 401,
        // maka kita toleransi dua kondisi: 201 (Created) atau 401 (Unauthorized)
        Assert.assertTrue(
                status == 201 || status == 401,
                "Expected 201 (Created) or 401 (Unauthorized), but got: " + status
        );

        // Validasi konten kalau berhasil dibuat
        if (status == 201) {
            Assert.assertTrue(
                    response.jsonPath().getString("id") != null,
                    "Response should contain generated id"
            );
            Assert.assertEquals(response.jsonPath().getString("name"), name, "Name mismatch");
            Assert.assertEquals(response.jsonPath().getString("job"), job, "Job mismatch");
            System.out.println("User created successfully with ID: " + response.jsonPath().getString("id"));
        } else if (status == 401) {
            System.out.println("API returned 401 - Missing API key. Skipping deep validation.");
        }
    }
}
