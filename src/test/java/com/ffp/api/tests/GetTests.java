package com.ffp.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.ffp_api_client.ResponseBuilder;

import io.restassured.response.Response;



public class GetTests {
	@Test

    public void validateGetById() {
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
            .when()
                .get("/posts/1");

        // Print full body for debugging
        String responseBody = response.getBody().asString();
        System.out.println("Actual Body:\n" + responseBody);

        // Apply general response validations (status code, content type, etc.)
        response.then()
                .spec(ResponseBuilder.resSpec_Login_200_OK(200))
                .spec(ResponseBuilder.responseHeaderCheck());

        // Manually assert the body content field to avoid whitespace issues
        String actualBodyField = response.jsonPath().getString("body");
        String expectedBodyField =
                "quia et suscipit\n" +
                "suscipit recusandae consequuntur expedita et cum\n" +
                "reprehenderit molestiae ut ut quas totam\n" +
                "nostrum rerum est autem sunt rem eveniet architecto";

        System.out.println("\n=== Comparison ===");
        System.out.println("Expected:\n" + expectedBodyField);
        System.out.println("\nActual:\n" + actualBodyField);

        Assert.assertEquals(actualBodyField, expectedBodyField, "Body field does not match!");
    }
}
