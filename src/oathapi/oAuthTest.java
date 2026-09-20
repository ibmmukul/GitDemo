package oathapi;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import poja.Api;
import poja.GetCourse;
import poja.WebAutomation;

public class oAuthTest {

	public static void main(String[] args) {
		
		String[] coursesTitles= {"Selenium Webdriver Java","Cypress","Protractor"};
		
		String responce=given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParam("grant_type", "client_credentials")
				.formParam("scope","trust").when().log().all()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
		
		System.out.println(responce);
		
		JsonPath js = new JsonPath(responce);
		
	String accessToken=	js.getString("access_token");
	
	System.out.println("Access Token is " +accessToken);
	
	String getresponse= given().queryParam("access_token", accessToken).when().log().all()
			.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
	
	System.out.println(getresponse);
	
	/// Below is deserilization 
	
	GetCourse gc= given().queryParam("access_token", accessToken).when().log().all()
			.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
	
	
	System.out.println(gc.getLinkedIn());
	System.out.println(gc.getUrl());
	String apicourceprice=gc.getCourses().getApi().get(1).getPrice();
				
	System.out.println(apicourceprice);
	
		
	    List<Api> apicoursepricedetails=gc.getCourses().getApi();
	    
	    for(int i=0;i<apicoursepricedetails.size();i++)
	    {
	    	if(apicoursepricedetails.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
	    	{
	    		String actualcourseprice=apicoursepricedetails.get(i).getPrice();
	    		System.out.println(actualcourseprice);
	    	}
	    }
	    
	    
	    //Get Course title of Web Automation
	    
	    
	    
	   List<WebAutomation> webcoursetitle= gc.getCourses().getWebAutomation();
	   ArrayList<String> arr1= new ArrayList<String>();
	   
	   for(int j=0;j<webcoursetitle.size();j++)
	   {
		   String wtitle=webcoursetitle.get(j).getCourseTitle();
		   
		   arr1.add(wtitle);
		  
	   }
	   
	   List<String> arr2  =Arrays.asList(coursesTitles);
	   
	   Assert.assertTrue(arr1.equals(arr2));
	   
	  
	}

}
