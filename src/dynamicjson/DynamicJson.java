package dynamicjson;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static  io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJson {
	
	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle)
	{
	    RestAssured.baseURI = "https://rahulshettyacademy.com";

	    String response = given().log().all()
	            .header("Content-Type", "application/json")
	            .body(payload.addBook(isbn, aisle))
	    .when()
	            .post("/Library/Addbook.php")
	    .then()
	            .log().all()
	            .assertThat().statusCode(200)
	            .extract().response().asString();

	    System.out.println("Response is " + response);

	    JsonPath js = ReusableMethods.rawtojson(response);

	    String id = js.get("ID");
	    System.out.println("Book ID: " + id);

	    // ==========================
	    // Get Book By Author Name
	    // ==========================

//	    String getBookResponse = given().log().all()
//	            .queryParam("AuthorName", "Mukul Sharma")
//	    .when()
//	            .get("/Library/GetBook.php")
//	    .then()
//	            .log().all()
//	            .assertThat().statusCode(200)
//	            .extract().response().asString();
//
//	    System.out.println("Get Book Response: " + getBookResponse);
//
	    // ==========================
	    // Delete Book
	    // ==========================

	    String deleteResponse = given().log().all()
	            .header("Content-Type", "application/json")
	            .body(payload.deleteBook(id))
	    .when()
	            .post("/Library/DeleteBook.php")
	    .then()
	            .log().all()
	            .assertThat().statusCode(200)
	            .extract().response().asString();

	    System.out.println("Delete Response: " + deleteResponse);
	}

	@DataProvider(name = "BooksData")
	public Object[][] getData()
	{
	    return new Object[][] {
	            {"abio","786"},
	            {"oiba","787"},
	            {"iobb","788"}
	    };
	}
}
