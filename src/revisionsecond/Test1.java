package revisionsecond;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class Test1 {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//Post Command
		
		String postresponse=given().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body(Test2.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", equalTo("Apache/2.4.52 (Ubuntu)")).extract().response().asString();
		
		JsonPath js1= ReusableMethods.rawtojson(postresponse);
		
		String scope=js1.getString("scope");
		
		String placeid=js1.getString("place_id");
		
		System.out.println(placeid);
		
		//Put Command
		
String newaddress="70 Summer walk, USA Test";
		
String updateaddress=given().queryParam("key", "qaclick123")
.header("Content-Type","application/json").body("{\r\n"
		+ "\"place_id\":\""+placeid+"\",\r\n"
		+ "\"address\":\""+newaddress+"\",\r\n"
		+ "\"key\":\"qaclick123\"\r\n"
		+ "}").when().put("maps/api/place/update/json").then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();


JsonPath js=ReusableMethods.rawtojson(updateaddress);
String message=js.getString("msg");

System.out.println(message);
		

String getresponse=given().queryParam("key", "qaclick123").queryParam("place_id", placeid).when().get("maps/api/place/get/json").then().assertThat().statusCode(200).extract().response().asString();
JsonPath js2=ReusableMethods.rawtojson(getresponse);

String actualaddress=js2.getString("address");

System.out.println(actualaddress);

Assert.assertEquals(actualaddress, newaddress);

System.out.println("pass");

		
		
		
		
		
		
		
	}

}
