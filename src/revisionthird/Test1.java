package revisionthird;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class Test1 {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String postresponse=given().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body(Test2.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.52 (Ubuntu)")).extract().response().asString();
		
		System.out.println("Post response is " +postresponse);
		
		JsonPath js1=ReusableMethods.rawtojson(postresponse);
	
		String placeid=js1.getString("place_id");
		
		String newaddress="70 Summer walk, USA Test";
		
		String putresponse=given().queryParam("key", "qaclick123").queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").when().put("maps/api/place/update/json").then().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated")).extract().response().asString();
		
		JsonPath js2=ReusableMethods.rawtojson(putresponse);
		
		String postmessage=js2.getString("msg");
		
		System.out.println(postmessage);
		
		
		String getresponse=given().queryParam("key", "qaclick123").queryParam("place_id", placeid).when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js3= ReusableMethods.rawtojson(getresponse);
		String actualaddress=js3.getString("address");
		
		System.out.println(actualaddress);
		
		Assert.assertEquals(actualaddress, newaddress);
		
		System.out.println("Pass");
		
		
		
		
	}

}
