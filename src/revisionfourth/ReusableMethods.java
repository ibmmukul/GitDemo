package revisionfourth;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath rawtojson(String response)
	{
		JsonPath js= new JsonPath(response);
		
		return js;
	}

}
