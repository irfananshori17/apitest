package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import requests.UserRequests;

public class BoundaryTests {

    UserRequests user = new UserRequests();

    @Test
    public void testGetUserWithIdZero() {
        System.out.println("\n=== Test: Get user with ID = 0 ===");
        Response response = user.getUser(0);

        int status = response.statusCode();
        long time = response.getTime();

        System.out.println("Response code: " + status);
        System.out.println("Response time: " + time + " ms");

        Assert.assertTrue(
                status == 404 || status == 401,
                "Expected 401 or 404, but got: " + status
        );
    }

    @Test
    public void testGetUserWithMaxIntId() {
        System.out.println("\n=== Test: Get user with ID = Integer.MAX_VALUE ===");
        Response response = user.getUser(Integer.MAX_VALUE);

        int status = response.statusCode();
        long time = response.getTime();

        System.out.println("Response code: " + status);
        System.out.println("Response time: " + time + " ms");

        Assert.assertTrue(
                status == 404 || status == 401,
                "Expected 401 or 404, but got: " + status
        );
    }

    @Test
    public void testGetUserWithNegativeId() {
        System.out.println("\n=== Test: Get user with negative ID ===");
        Response response = user.getUser(-1);

        int status = response.statusCode();
        long time = response.getTime();

        System.out.println("Response code: " + status);
        System.out.println("Response time: " + time + " ms");

        Assert.assertTrue(
                status == 404 || status == 401,
                "Expected 401 or 404, but got: " + status
        );
    }
}
