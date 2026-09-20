package lastfinal;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class LastFinal {

	@Test
	public void finalTouch()
	{
		
		int sum=0;
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		//1 Print No of courses returned by API
		
		int numberofcourses= js.getInt("courses.size()");
		
		System.out.println(" Number of courses are " +numberofcourses);
		
		
		//2 .Print Purchase Amount
		
		int purchasedamount= js.getInt("dashboard.purchaseAmount");
		
		System.out.println(" Purchased amount is " +purchasedamount);
		
		//3. Print Title of the first course
		
		String titleoffirstcourse=js.getString("courses[0].title").toString();
		
		System.out.println(" Title of first course is " +titleoffirstcourse);
		
		//4 Print All course titles and their respective Prices
		
		for (int i=0;i<numberofcourses;i++)
		{
			String allcoursestitle=js.getString("courses["+i+"].title").toString();
			
			System.out.println(" All courses title are " +allcoursestitle);
			
			int courseprice=js.getInt("courses["+i+"].price");
			
			System.out.println("Course price is " +courseprice);
			
			
			
		}
		
		// 5 . Print no of copies sold by RPA Course
		
		for (int j=0;j<numberofcourses;j++)
		{
			String allcourses=js.getString("courses.["+j+"].title").toString();
					if(allcourses.equalsIgnoreCase("rpa"))
					{
						int copysoldrpa=js.getInt("courses.["+j+"].copies");
						System.out.println(copysoldrpa);
						break;
					}
		}
		
		//6 Verify if Sum of all Course prices matches with Purchase Amount
		for (int k=0;k<numberofcourses;k++)
		{
			int price=js.getInt("courses["+k+"].price");
			int copies=js.getInt("courses["+k+"].copies");
			int amount=price*copies;
			sum=sum+amount;
			
			
		}
		
		System.out.println(sum);
		
		Assert.assertEquals(sum, purchasedamount);
		
		
	}
}
