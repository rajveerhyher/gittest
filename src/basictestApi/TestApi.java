package basictestApi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;


public class TestApi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//To validate if Add place API is working
		
		//Given- all input details
		//When- Submit the API- resource, http method
		//Then- Validate the response
		
		//add  place-> update place with new address -> get place to validate new address is present in response
		
		
	//Add place	
RestAssured.baseURI="https://rahulshettyacademy.com";
String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
.body(payload.AddPlace()).when().post("maps/api/place/add/json")
.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

System.out.println(response);

JsonPath js=new JsonPath(response); //parsing JSON
String placeid=js.getString("place_id");

System.out.println(placeid);

//update place
String newaddress = "70 winter walk, USA";
given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
.body("{\r\n"
		+ "\"place_id\":\""+placeid+"\",\r\n"
		+ "\"address\":\""+newaddress+"\",\r\n"
		+ "\"key\":\"qaclick123\"\r\n"
		+ "}").when().put("maps/api/place/update/json")
.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

//get place

String getPlaceResponse= given().log().all().queryParam("key", "qaclick123")
.queryParam("place_id", placeid)
.when().get("maps/api/place/get/json")
.then().assertThat().log().all().statusCode(200).extract().asString();

 JsonPath js1 =  ReUsableMethods.rawToJson(getPlaceResponse);

String actualAddress= js1.getString("address");
System.out.println(actualAddress);
Assert.assertEquals(actualAddress, newaddress);
//cucumber Junit, Testng

	}

	

}
