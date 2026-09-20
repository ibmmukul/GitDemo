package FinalTouchRevision;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.testng.Assert;

public class AddPlace {

	public static void main(String[] args) throws IOException {
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String getpostresponse=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes((Path.of("C:\\Users\\admin\\learning\\APILearning\\APILearning\\target\\JsonFile\\AddAddresss.Json"))))).when().log().all().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("status", equalToIgnoringCase("OK")).extract().response().asString();
		
		System.out.println(getpostresponse);
		
		JsonPath js= new JsonPath(getpostresponse);
		
		String placeid=js.getString("place_id");
		
		String address = js.getString("address");
		
		System.out.println("place id is  " +placeid);
		
		System.out.println("address is " +address);
		
		String newaddress="this is test";
		
		String getputresponse=given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json")
				.body("{\r\n"
						+ "\"place_id\":\""+placeid+"\",\r\n"
						+ "\"address\":\""+newaddress+"\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n"
						+ "}\r\n"
						+ "").when().log().all().put("maps/api/place/update/json").then().log().all()
				.assertThat().statusCode(200).body("msg", equalToIgnoringCase("Address successfully updated")).extract().response().asString();
		
		
		JsonPath js1= new JsonPath(getputresponse);
		
		String updatedaddress=js1.getString("address");
		
		System.out.println(updatedaddress);
		
		String getresponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid).when()
				.log().all().get("/maps/api/place/get/json").then().assertThat().statusCode(200).body("address", equalToIgnoringCase(newaddress)).extract().response().asString();
		
		JsonPath js2= new JsonPath(getresponse);
		
		String adterupdateaddress=js2.getString("address");
		
		Assert.assertEquals(adterupdateaddress, newaddress);
		
		System.out.println(" all is OK");
		
		
		

	}

}
