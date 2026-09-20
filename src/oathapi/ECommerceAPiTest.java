package oathapi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import poja.LoginRequest;
import poja.LoginResponse;
import poja.OrderDetail;
import poja.Orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;



public class ECommerceAPiTest {

	public static void main(String[] args) {
		
	RequestSpecification	req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
	
	LoginRequest loginRequest= new LoginRequest();
	loginRequest.setUserEmail("mukul.sharma9@gmail.com");
	loginRequest.setUserPassword("Welcome10@ibm");

	RequestSpecification reqLogin=given().relaxedHTTPSValidation().log().all().spec(req).body(loginRequest);
	LoginResponse loginResponse=reqLogin.when().log().all().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);
	
	String token =loginResponse.getToken();
	String userid=loginResponse.getUserId();
	System.out.println(token);
	System.out.println(userid);
	
	//Add Product 
	
	RequestSpecification addProductBaseReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("Authorization", token).build();
	
	RequestSpecification reqAddProduct=given().log().all().spec(addProductBaseReq).param("productName", "LaptopTest").param("productAddedBy", userid)
	.param("productCategory", "fashion").param("productSubCategory", "tshirt").param("productPrice", "11500")
	.param("productDescription", "Addias Originals").param("productFor", "men")
	.multiPart("productImage",new File("C:\\Users\\admin\\OneDrive\\Desktop\\My_Picture.jpg"));
	
	String addProductResponse=reqAddProduct.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
	
	JsonPath js = new JsonPath(addProductResponse);
	
	String productId=js.getString("productId");
	
	//Create Order
	
	
	
	RequestSpecification createOrderBaseReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("authorization", token).setContentType(ContentType.JSON).build();
	
	OrderDetail orderDetail= new OrderDetail();
	orderDetail.setCountry("India");
	orderDetail.setProductOrderedId(productId);
	
	List<OrderDetail> orderDetailList= new ArrayList<OrderDetail>();
	orderDetailList.add(orderDetail);
	
	
	Orders orders= new Orders();
	orders.setOrders(orderDetailList);
	
	RequestSpecification createOrderReq=given().log().all().spec(createOrderBaseReq).body(orders);
	
	String responseAddOrder=createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
	System.out.println(responseAddOrder);
	
//Get Order Details
	
	JsonPath js1 = new JsonPath(responseAddOrder);
	
	String orderId = js1.getString("orders[0]");
	
	RequestSpecification getOrderBaseReq = new RequestSpecBuilder()
	        .setBaseUri("https://rahulshettyacademy.com")
	        .addHeader("authorization", token)
	        .build();

	RequestSpecification getOrderReq = given()
	        .log().all()
	        .spec(getOrderBaseReq)
	        .queryParam("id", orderId);

	String getOrderResponse = getOrderReq
	        .when()
	        .get("/api/ecom/order/get-orders-details")
	        .then()
	        .log().all()
	        .extract()
	        .response()
	        .asString();

	System.out.println(getOrderResponse);
	
	//Delete the Product 
	
	RequestSpecification deleteProdBaseReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("authorization", token).setContentType(ContentType.JSON).build();
	
	RequestSpecification deleteProdReq=given().log().all().spec(deleteProdBaseReq).pathParam("productId", productId);
	
	String deleteProductResponse=deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}")
	.then().log().all().extract().response().asString();
	
JsonPath js2 = new JsonPath(deleteProductResponse);
	
	String message = js2.get("message");
	
	Assert.assertEquals("Product Deleted Successfully", message);
	
	
	
	}

}
