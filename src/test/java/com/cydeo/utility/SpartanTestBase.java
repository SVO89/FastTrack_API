package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
public abstract class SpartanTestBase {

    @BeforeAll
    static void init(){
        baseURI = "http://54.164.57.236:8000";
        basePath = "/api";
    }

    @AfterAll
    static void destroy(){
        reset();
    }



}
