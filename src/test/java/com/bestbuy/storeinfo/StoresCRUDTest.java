package com.bestbuy.storeinfo;

import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class StoresCRUDTest extends TestBase {


    @Test// get full list
    public void test001() {
        Response response=given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test // get Store data single data
            public void test002() {
        Response response = given()

                .pathParam("id", "6")
                .when()
                .get("/{id}");
        response.then().statusCode(200);
                response.prettyPrint();
    }

    @Test //Post new data
    public void test003() {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("Minnetonkaacaaa");
        storesPojo.setType("BigBozxxx");
        storesPojo.setAddress("13513 Riygedale Drr");
        storesPojo.setCity("Hopkiins");
        storesPojo.setState("FL");
        storesPojo.setZip("12245");

        Response response = given()
              .header("Content-Type","application/json")
                .body(storesPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }
    @Test // update with id
    public void test004(){
        StoresPojo storesPojo = new StoresPojo();
     //   storesPojo.setType("SmallBoxxx");
        storesPojo.setAddress("1234 Riygedale eng");
        storesPojo.setCity("NW");
        Response response = given()
                .header("Content-Type","application/json")
                .pathParam("id","8946")
                .body(storesPojo)
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
@Test
    public void test005() {
        Response response =given()
                .pathParam("id","8946")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test // retrieve id and validate id has delete
    public void test006() {
        Response response =given()
                .pathParam("id","8946")
                .when()
                .get("/{id}");
        response.then().statusCode(404);
        response.prettyPrint();

    }
}
