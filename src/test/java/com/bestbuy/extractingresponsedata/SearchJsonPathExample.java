package com.bestbuy.extractingresponsedata;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;


public class
SearchJsonPathExample {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    // 1) Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    // 2) Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        //List<Integer> numofId=response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + total);
        System.out.println("------------------End of Test---------------------------");

    }

    // 3) Extract the name of 5th store
    @Test
    public void test003() {
        String nameOfStore = response.extract().path("data[5].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The first product name is : " + nameOfStore);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4) Extract the names of all the store
    @Test
    public void test004() {
        //String allStoreName =response.extract().path("data.name[0]");
        // HashMap<String,System>allStoreName = response.extract().path("data.name[]");
        List<HashMap<String, System>> allStoreName = response.extract().path("data.name.findAll{'name'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store list are : " + allStoreName);
        System.out.println("------------------End of Test---------------------------");

    }

    // 5)Extract the storeId of all the store
    @Test
    public void test005() {

        List<HashMap<String, System>> storeId = response.extract().path("data.id.findAll{'id'}");
        //  List<Integer> storeId=response.extract().path("data[0].id");
        //int sizes=storeId.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the storeId : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6) Print the size of the data list
    @Test
    public void test006() {
        List<Integer> dataList = response.extract().path("data");
        int sizes = dataList.size();

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list : " + sizes);
        System.out.println("------------------End of Test---------------------------");
    }

    // 7) Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<Integer, ?>> values = response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the value of Store 'St Cloud' " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8) Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        String addressOfStore = response.extract().path("data[5].address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Address Of the Store 'Rochester' : " + addressOfStore);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9) Get all the services of 8th store
    @Test
    public void test009() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data[8].services.findAll{it.services='services'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the services of 8th stores " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10) Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        //Not working
        List<HashMap<String, ?>> storeServices = response.extract().path("data.services.name.find{'Windows Store'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store where service name is: " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    // 11) Get all the storeId of all the store
    @Test
    public void test011() {

        List<HashMap<Integer, ?>> values = response.extract().path("data.services.id.findAll{'id'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the storeID Of All store" + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // 12) Get id of all the store
    @Test
    public void test012() {
        List<HashMap<Integer, ?>> id = response.extract().path("data.id.findAll{'id'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get Id OF All store:" + id);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the store names Where state = MN
    @Test
    public void test013() {
        // not working
      //  List<HashMap<String, ?>> state = response.extract().path("data.state.findAll{'MN'}");
        List<String> state = response.extract().path("data.state.findAll{'MN'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get Id OF All store:" + state);
        System.out.println("------------------End of Test---------------------------");
    }


}
