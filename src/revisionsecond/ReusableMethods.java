package revisionsecond;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath rawtojson(String response)
	{
		JsonPath js1= new JsonPath(response);
		//JsonPath js2= new JsonPath(response);
		
		return js1;
	}

}
