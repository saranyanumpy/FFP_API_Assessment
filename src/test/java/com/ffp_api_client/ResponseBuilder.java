package com.ffp_api_client;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

public class ResponseBuilder {

    public static ResponseSpecification responseHeaderCheck() {
        return new ResponseSpecBuilder()
                .expectHeader("Connection", "keep-alive")
                .build();
    }

    public static ResponseSpecification resSpec_Login_200_OK(int expectedStatusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .expectContentType(ContentType.JSON)
                .expectBody("userId", equalTo(1))
                .expectBody("id", equalTo(1))
                .expectBody("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
                .build();
    }
}
