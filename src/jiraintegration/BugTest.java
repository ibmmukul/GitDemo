package jiraintegration;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static  io.restassured.RestAssured.*;

import java.io.File;

public class BugTest {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://mukulsharma9.atlassian.net/";
		
		String createIssueResponse=given().header("Content-Type","application/json")
		.header("Authorization","Basic bXVrdWwuc2hhcm1hOUBnbWFpbC5jb206QVRBVFQzeEZmR0YweG95dEF4TTNESEhMNmRqZUlPRGJZZTVJOUJET09SdE4wUkxjdzRXOW9leEk1N1FEcHpxMzhQOGlpME9VN01tZEpSXzZubGdjRTlhMHl2LU80LXRPakJIUjJxMElHeTZNZ1hNMUEyb3JvR01tUWdycU9CMDlZdHdBUVdNa1BQQ1d1OEEwRm5IcTY3RHdNdzVxQXp0b0FZVzd0WHJnbGtBdkNNNjZjajRqN1RnPTMxRjBCMTI5")
		.body("{\r\n"
				+ "  \"fields\": {\r\n"
				+ "    \"project\": {\r\n"
				+ "      \"key\": \"RJ\"\r\n"
				+ "    },\r\n"
				+ "    \"summary\": \"Links are not working from rest assured\",\r\n"
				+ "    \"issuetype\" :\r\n"
				+ "    {\r\n"
				+ "      \"name\": \"Bug\"\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}").log().all().when().post("rest/api/3/issue").then().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(createIssueResponse);
		
		String issueId=js.getString("id");
		
		System.out.println(issueId);
		
		given().pathParam("key", issueId)
		.header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic bXVrdWwuc2hhcm1hOUBnbWFpbC5jb206QVRBVFQzeEZmR0YweG95dEF4TTNESEhMNmRqZUlPRGJZZTVJOUJET09SdE4wUkxjdzRXOW9leEk1N1FEcHpxMzhQOGlpME9VN01tZEpSXzZubGdjRTlhMHl2LU80LXRPakJIUjJxMElHeTZNZ1hNMUEyb3JvR01tUWdycU9CMDlZdHdBUVdNa1BQQ1d1OEEwRm5IcTY3RHdNdzVxQXp0b0FZVzd0WHJnbGtBdkNNNjZjajRqN1RnPTMxRjBCMTI5")
		.multiPart("file",new File("C:\\Users\\admin\\Downloads\\code6.txt")).log().all()
		.when().post("/rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);

	}

}
