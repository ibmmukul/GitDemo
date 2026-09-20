package files;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Test.*;
import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumofCourses()
	{
		
		int sum=0;
		
		JsonPath js= new JsonPath(payload.CoursePrice());
		
		int numberofcourses=js.getInt("courses.size()");
		
		int purchasedamount=js.getInt("dashboard.purchaseAmount");
		
		System.out.println(purchasedamount);
		
		for(int k=0;k<numberofcourses;k++)
		{
			int price=js.getInt("courses["+k+"].price");
			int copies=js.getInt("courses["+k+"].copies");
			
			int amount=price*copies;
			//System.out.println("total amount is " +amount);
			sum=sum+amount;
			
			
		}
		
System.out.println(" total sum is "+sum);
		
		
		
		
		
		
	}
	
	
	
	

}
