package requests;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import utilities.ConfigReader;

public class UserRequests {

    private static final String BASE_URL = ConfigReader.get("base.url");
    private static final String API_KEY = ConfigReader.get("api.key"); // bisa null

    private io.restassured.specification.RequestSpecification prepare() {
        io.restassured.specification.RequestSpecification req = given().baseUri(BASE_URL)
                .header("Content-Type", "application/json");
        if (API_KEY != null && !API_KEY.trim().isEmpty()) {
            // header Authorization konvensional, sesuaikan jika API menggunakan header berbeda
            req.header("Authorization", "Bearer " + API_KEY);
        }
        return req;
    }

    public Response getUser(int id) {
        return prepare()
                .when()
                .get("/users/" + id)
                .then()
                .extract()
                .response();
    }

    public Response createUser(String name, String job) {
        String body = String.format("{\"name\":\"%s\", \"job\":\"%s\"}", name, job);

        return prepare()
                .body(body)
                .when()
                .post("/users")
                .then()
                .extract()
                .response();
    }
}
