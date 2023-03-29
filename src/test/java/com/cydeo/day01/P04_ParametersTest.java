package com.cydeo.day01;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P04_ParametersTest extends SpartanTestBase{

    @DisplayName("GET /spartans/{id} with Path Param example")
    @Test
    void oathParam(){

        Response response = given().log().all().accept(ContentType.JSON)
                .pathParam("id", 5).
                when().get("/spartans/{id}");

        Assertions.assertEquals(HttpStatus.SC_OK,response.statusCode());
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"gender\") = " + response.path("gender"));
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"phone\") = " + response.path("phone"));

    }

}
