package files;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {

		
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		//1 Print No of courses returned by API
		
		int numberofcourses=js.getInt("courses.size()");
		
		System.out.println("number of courses are " +numberofcourses);
		
		//2 Print Purchase Amount
		
		int purchaseamount=js.getInt("dashboard.purchaseAmount");
		
		System.out.println("purchase amount is " +purchaseamount);
		
		//3. Print Title of the first course
		
		
		String firsttitle=js.getString("courses[0].title");
		
		System.out.println(" first title is " +firsttitle);
		
		//4 . Print All course titles and their respective Prices
		
		for(int i=0;i<numberofcourses;i++)
		{
			String allcourses=js.getString("courses.["+i+"].title").toString();
			
			int courseprice=js.getInt("courses.["+i+"].price");
			
			System.out.println(" courses are " +allcourses);
			
			System.out.println(" price of " +allcourses+ " is " +courseprice);
			
			
		}
		
		for(int j=0;j<numberofcourses;j++)
		{
			String allcourses=js.getString("courses.["+j+"].title").toString();
			if(allcourses.equalsIgnoreCase("rpa"))
			{
				int numberofcopiesofrpa=js.getInt("courses.["+j+"].copies");
				System.out.println("RPA copies sold is  " +numberofcopiesofrpa);
				
			}
		}
		
		//6 6. Verify if Sum of all Course prices matches with Purchase Amount
		
		int sum =0;
		
		for(int k=0;k<numberofcourses;k++)
		{
			int price=js.getInt("courses["+k+"].price");
			int copies=js.getInt("courses["+k+"].copies");
			
			int amount=price*copies;
			//System.out.println("total amount is " +amount);
			sum=sum+amount;
			
			
		}
		
		System.out.println(" total sum is "+sum);
		
		Assert.assertEquals(purchaseamount, sum); 
		
		
		
			
		
		
		
		
	}

}
