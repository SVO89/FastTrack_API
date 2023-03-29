package com.cydeo.day01;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class P03_SpartanTest extends SpartanTestBase {

    @Test
    void getAllSpartans(){


         // Send request to Spartan URL and save the response
         // Accept application/json
        Response response = RestAssured.given().accept(ContentType.JSON).when()
                .get("/spartans");

         // Get /spartans
         // Response
        response.prettyPrint();

         //  Content-Type
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

         //  Status Code
        Assertions.assertEquals(HttpStatus.SC_OK,response.statusCode());

         //  Get me first spartan gender
        System.out.println("Get me first spartan gender");
        System.out.println("response.path(\"gender[0]\") = " + response.path("gender[0]"));
        System.out.println("response.path(\"[0].gender\") = " + response.path("[0].gender"));
        System.out.println("-------------------");

        //  Get me first spartan name
        System.out.println("Get me first spartan name");
        System.out.println("response.path(\"name[0]\") = " + response.path("name[0]"));
        System.out.println("response.path(\"[0].name\") = " + response.path("[0].name"));
        System.out.println("-------------------");

        //  Get me all spartans names
        System.out.println("Get me all spartans names");
        System.out.println("response.path(\"name\") = " + response.path("name"));
        List <String> allNames = response.path("name");
        System.out.println(allNames);




    }

}
