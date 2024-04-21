package stepDefinations;


import Files.Payload;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefination
{
	ResponseSpecification resSpec;
	RequestSpecification req;
	Response response;
	@Given("create user payload")
	public void create_user_payload() throws FileNotFoundException {
		//RestAssured.baseURI= "https://reqres.in";
		PrintStream log= new PrintStream(new FileOutputStream("Logging.txt"));
		RequestSpecification reqSpec= new RequestSpecBuilder().setBaseUri("https://reqres.in")
				.addQueryParam("key","pass123").
				addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).
				setContentType(ContentType.JSON).build();
		req= given().spec(reqSpec).body(Payload.addPlace());
		//generic fields are mapped in response builder
		resSpec =new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();
		
	 
			 
	}
	@When("user call {string} with post http request")
	public void user_call_with_post_http_request(String string)
	{
		response =req.when().post("api/users").then().log().all().spec(resSpec).extract().response();
	
	}
	@Then("the API  call get success with status code {int}")
	public void the_api_call_get_success_with_status_code(Integer int1)
	{
	
	    assertEquals(201,response.getStatusCode());
	}
	@Then("{string} in response body {string}")
	public void in_response_body(String KeyValue, String ExpectedValue)
	{
	   String resp= response.asString();
		System.out.println("resp :"+resp);
		System.out.println("resp get status line: " +response.getStatusLine());
		assertTrue(response.getStatusLine().contains(ExpectedValue.toString()));
		//assertEquals();
		
//	   JsonPath js= new JsonPath(resp);
//		System.out.println("js : " +js.ge);
		System.out.println("ExpectedValue.toString() : " +ExpectedValue.toString());
//		assertEquals(ExpectedValue.toString(),js.get(KeyValue.toString()));
//		JsonPath js= new JsonPath(resp);
//		System.out.println("js : " +js);
//		// jsonpath takes string and convert it to json .also help to parse json.
//		String st= js.getString();
//		System.out.println("st : " +st);
//		System.out.println("Status : " +st);
	}

	
	
	

}
