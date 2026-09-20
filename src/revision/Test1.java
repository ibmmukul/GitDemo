package revision;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class Test1 {
	
	
	public static void main(String[] args) {
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response=given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Test2.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.52 (Ubuntu)")).extract().response().asString();
		
		System.out.println("Response is " +response);
		
		JsonPath js= new JsonPath(response);
		
		String placeid=js.get("place_id");
		
		String scopevalue =js.get("scope");
		
		System.out.println("Scope is " +scopevalue);
		
		System.out.println("Place id is " +placeid);
		
		//update the place
		
		String newaddress="70 Summer walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().log().all().put("maps/api/place/update/json").then().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated")).header("Server", equalTo("Apache/2.4.52 (Ubuntu)"));
		
		//Get the address
		
		String getresponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid).when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		System.out.println(getresponse);
		
		
		JsonPath js1= new JsonPath(getresponse);
		String actualaddress=js1.getString("address");
		
		System.out.println(actualaddress);

		if(actualaddress.equalsIgnoreCase(newaddress))
		{
			System.out.println("Address is same");
		}
		else
		{
			System.out.println("Address is not same");
		}

Assert.assertEquals(actualaddress, newaddress);

				
		
		
		
	}

}
