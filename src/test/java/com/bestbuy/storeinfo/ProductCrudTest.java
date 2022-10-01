package com.bestbuy.storeinfo;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductCrudTest extends TestBase {
    @Test// get full list
    public void test001() {
        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test // get Store data single data
    public void test002() {
        Response response = given()
                .pathParam("id", "150115")
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test //Post new data
    public void test003() {
        ProductPojo productpojo = new ProductPojo();
        productpojo.setName("Dysonn");
        productpojo.setType("hoover");
        productpojo.setPrice(2);
        productpojo.setUpc("041333825022");
        productpojo.setShipping(11);
        productpojo.setDescription("dyson hoover is best");
        productpojo.setManufacturer("dyyson");
        productpojo.setModel("Dysonn1");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(productpojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();

    }

    @Test // update product with id
    public void test004() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setType("hover");
        productPojo.setPrice(3);

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", "9999707")
                .body(productPojo)
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void test005() {
        Response response = given()
                .pathParam("id", "9999707")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test // retrieve id and validate id has delete
    public void test006() {
        Response response =given()
                .pathParam("id","9999707")
                .when()
                .get("/{id}");
        response.then().statusCode(404);
        response.prettyPrint();

    }
}
