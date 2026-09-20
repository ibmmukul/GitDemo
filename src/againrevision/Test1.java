package againrevision;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
public class Test1 {
	
	public static void main(String[] args) {
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//Post Method
		
		String response=given().queryParam("key", "qaclick123").header("Content-Type","application/json\r\n"
				+ "").body(Test2.AddPlace()).post("maps/api/place/add/json").then().assertThat()
		.statusCode(200).header("Server", equalTo("Apache/2.4.52 (Ubuntu)"))
		.body("scope", equalTo("APP")).extract().response().asString();
		
		System.out.println("Response is " +response);
		
		JsonPath js= new JsonPath(response);
		
		String placeid=js.get("place_id");
		
		System.out.println("PLace id is " +placeid);
		
		//Put Method to update the records 
		
		String newaddress="70 Summer walk, USA Test";
		
		String updateaddress=given().queryParam("key", "qaclick123").header("Content-Type","application/json\r\n"
				+ "").body("{\r\n"
						+ "\"place_id\":\""+placeid+"\",\r\n"
						+ "\"address\":\""+newaddress+"\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n"
						+ "}").when().put("maps/api/place/update/json").then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();

		//String messageexpected
		
		JsonPath jsmessageexpected= new JsonPath(updateaddress);
		String actualmessage=jsmessageexpected.getString("msg");
		System.out.println(actualmessage);
		
		String actualaddress=given().queryParam("key", "qaclick123").queryParam("place_id", placeid).when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1=ReusableMethods.rawtojson(actualaddress);
		
		String expectedaddress=js1.getString("address");
		
		Assert.assertEquals(newaddress, expectedaddress);
		
		
		
		System.out.println(expectedaddress);
		
	}

}
