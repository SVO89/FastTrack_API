package com.cydeo.day01;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

class P02_BeforeAllAndAfterAll {

    @BeforeAll
    static void init(){
        baseURI = "http://54.164.57.236:1000";
        basePath = "/ords/hr";
    }

    @AfterAll
    static void destroy(){
        reset();
    }


    void simpleGETRequest(){

        Response response = get("/regions");

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

        Response response = get("http://54.164.57.236:1000/ords/hr/employees/100").prettyPeek();

        String firstName = response.path("first_name");
        System.out.println(firstName);

        String lastName = response.path("last_name");
        System.out.println(lastName);

        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);

        assertEquals(200,statusCode);
        assertEquals("Steven", firstName);
        assertEquals("King", lastName);
        assertEquals("application/json",contentType);

        // OR

        assertEquals(HttpStatus.SC_OK,statusCode);
        assertEquals("Steven", response.path("first_name"));
        assertEquals("King", response.path("last_name"));
        assertEquals("application/json",response.contentType());


    }










}
