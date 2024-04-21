package stepDefinations;

import Files.Payload;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinationLibrary
{
	RequestSpecification reqSpec;
	RequestSpecification req;
	ResponseSpecification resSpec;
	Response response;
	
	
	@Given("addBook for users in library")
	public void add_book_for_users_in_library()
	{
		 reqSpec= new RequestSpecBuilder().setBaseUri("http://216.10.245.166")
				.addQueryParam("key","pass123").setContentType(ContentType.JSON).build();
		
		req=given().log().all().spec(reqSpec).body(Payload.addBook());
		
		resSpec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}
	
	@When("user call {string} with http post request")
	public void user_call_with_http_post_request(String string)
	{
		response =req.when().post("Library/Addbook.php").then().log().all().spec(resSpec).extract().response();
	}
	
	@Then("api will give response with status code {int}")
	public void api_will_give_response_with_status_code(Integer int1)
	{
	  assertEquals(200,response.getStatusCode());
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String ExpectedValue)
	{
		String resp= response.asString();
		System.out.println("resp :"+resp);
		System.out.println("resp get status line: " +response.getStatusLine());
		assertTrue(response.getStatusLine().contains(ExpectedValue.toString()));
		JsonPath js=  new JsonPath(resp);
		System.out.println("ID:" +js.get("ID"));
		
	}
	@Given("deleteBook for users from library")
	public void delete_book_for_users_from_library()
	{
		reqSpec= new RequestSpecBuilder().setBaseUri("http://216.10.245.166")
				.addQueryParam("key","pass123").setContentType(ContentType.JSON).build();
		
		req=given().log().all().spec(reqSpec).body(Payload.deleteBook());
		
		resSpec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}
	@When("user call {string} with http delete request")
	public void user_call_with_http_delete_request(String string)
	{
		
		response= req.when().post("Library/DeleteBook.php").then().log().all().spec(resSpec).extract().response();
	}
	
	@Then("{string} in Delete response body is {string}")
	public void in_delete_response_body_is(String Key, String Value)
	
	{
		String Delresp= response.asString();
		System.out.println("DELresp :"+Delresp);
		assertEquals("\"msg\":\"book is successfully deleted\"",response.body().equals("\"msg\":\"book is successfully deleted\""));
		
	
	}
	
	
}
