package graphql;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

public class GraphQLScript {

	public static void main(String[] args) {
		
//Query		
		int characterId=26519;
	String response=	given().log().all().header("content-type","application/json")
		.body("{\"query\":\"query($characterId :Int!)\\n{\\n  character(characterId: $characterId)\\n  {\\n    name\\n    gender\\n    status\\n    id\\n    type\\n  }\\n \\n  characters(filters:{name: \\\"Rahul\\\"})\\n{\\n  info\\n  {\\n    count\\n  }\\n  result\\n  {\\n    name\\n    type\\n    id\\n  }\\n}\\n}\\n\",\"variables\":{\"characterId\":"+characterId+"}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();
	
	System.out.println(response);
	
	JsonPath js = new JsonPath(response);
String charname=	js.get("data.character.name");
System.out.println(charname);

//mutation
String newcharname="Baskin Robin";

String mutationresponse=	given().log().all().header("content-type","application/json")
.body("{\"query\":\"mutation($locationName:String!,$characterName:String!,$episodeName:String!)\\n{\\n  createLocation(location:{name:$locationName,type:\\\"Southzone\\\",dimension:\\\"123\\\"})\\n  {\\n    id\\n  }\\n  \\n  createCharacter(character:{name:$characterName,type:\\\"Macho\\\",status:\\\"dead\\\",\\n    species:\\\"fantasy\\\",gender:\\\"male\\\",image:\\\"png\\\",locationId:35122,originId:35122})\\n  \\n  {\\n    id\\n    \\n  }\\n  createEpisode(episode:{name:$episodeName,air_date:\\\"june 1980\\\",episode:\\\"prime\\\"})\\n  {\\n    id\\n  }\\n  deleteLocations(locationIds:[35127,26514,23789])\\n  {\\n    locationsDeleted\\n  }\\n}\\n\",\"variables\":{\"locationName\":\"Australia\",\"characterName\":\""+newcharname+"\",\"episodeName\":\"Manifest\"}}")
.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();

System.out.println(mutationresponse);

//JsonPath js1 = new JsonPath(mutationresponse);



	}

}
