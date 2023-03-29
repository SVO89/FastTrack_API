package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P01_SimpleGETRequest {



    @Test
    void simpleGETRequest(){

        Response response = RestAssured.get("http://54.164.57.236:1000/ords/hr/regions");

        // Print response
        response.prettyPeek();
        System.out.println("-------------------------------");

        // Get headers
        System.out.println("response.getHeaders() = " + response.getHeaders());
        System.out.println("--------------------------");

        // Get Content-Type
        System.out.println("response.getContentType() = " + response.getContentType());
        System.out.println("--------------------------");

        // Get Status Code
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        response.headers().hasHeaderWithName("Date");
        System.out.println("--------------------------");

        // Get Date
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        System.out.println("--------------------------");

        // Verify response has Date
        System.out.println("response.headers().hasHeaderWithName(\"Date\") = " + response.headers().hasHeaderWithName("Date"));
        System.out.println("--------------------------");

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        System.out.println("--------------------------");

        // Verify response body has "Europe"
        System.out.println("response.asString().contains(\"Europe\") = " + response.asString().contains("Europe"));
        System.out.println("--------------------------");

    }

    /**
     * 1. Send request to HR url and save the response
     * 2. GET /employees/100
     * 3. Store response in Response Object that comes from the request
     * 4. Print out the following:
     *      - First Name
     *      - Last Name
     *      - Verify status code is 200
     *      - Verify first name is "Steven"
     *      - Verify content-Type is application/json
     */
    @DisplayName("GET /employees/100")
    @Test
    void getOneEmployee(){

        Response response = RestAssured.get("http://54.164.57.236:1000/ords/hr/employees/100").prettyPeek();

        String firstName = response.path("first_name");
        System.out.println(firstName);

        String lastName = response.path("last_name");
        System.out.println(lastName);

        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);

        Assertions.assertEquals(200,statusCode);
        Assertions.assertEquals("Steven", firstName);
        Assertions.assertEquals("King", lastName);
        Assertions.assertEquals("application/json",contentType);

        // OR

        Assertions.assertEquals(HttpStatus.SC_OK,statusCode);
        Assertions.assertEquals("Steven", response.path("first_name"));
        Assertions.assertEquals("King", response.path("last_name"));
        Assertions.assertEquals("application/json",response.contentType());


    }
}
