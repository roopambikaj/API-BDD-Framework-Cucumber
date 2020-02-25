package stepDefinitions;

//import org.junit.Assert;
import org.junit.runner.RunWith;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utilities.Utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.assertj.core.api.Assertions;

//this is for assert import


@RunWith(Cucumber.class)
public class APIValidationDefinitions extends Utils {

	Response response;
	RequestSpecification res;
	ResponseSpecification resspec;
	
	@Given("I call GET {string}")
	public void i_call_GET(String string) throws FileNotFoundException {
	    // Write code here that turns the phrase above into concrete actions
		 System.out.println("API called");
	    System.out.println(string);  		
	   
	    resspec = new  ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();	    
	    res = given().spec(requestSpecification());
	    
	}

	@Then("The response status should be {int}")
	public void the_response_status_should_be(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		response =res.when().get("/").
		then().spec(resspec).extract().response();
		assertEquals(response.getStatusCode(),200);
		System.out.println("Status code" + int1);
	}

	@Then("The response body should be valid json")
	public void the_response_body_should_be_valid_json() {
	    // Write code here that turns the phrase above into concrete actions
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		js.get("name");
		
	   
	}
	@Then("The value of {string} should be {string}")
	public void the_value_of_someValue_should_be_expectedValue(String keyValue, String expectedValue) {
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
	
		assertEquals(js.get(keyValue),expectedValue);
		
	}
	@Then("The value list of {string} should have name of {string}")
	public void the_value_list_of_someValue_should_have_name(String keyValue, String expectedValue){
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
//
	List<String> jsObject = js.getJsonObject(keyValue);

		Assertions.assertThat(jsObject).contains(expectedValue);
		
	}
	
	
//	Scenario 2:
	
	 @Given("^User should be added new through POST \"([^\"]*)\"$")
	    public void user_should_be_added_new_through_post_something(String strArg1) throws Throwable {
		 System.out.println("API called");
		    System.out.println(strArg1);  	
		      
		    res = given().spec(requestSpecification());
		   
	    }

	    @When("^the body should contain \"([^\"]*)\" and \"([^\"]*)\"$")
	    public void the_body_should_contain_something_and_something(String name, String age) throws Throwable {
	    	
	    	String payload = "{\n" +
	    	        "  \"name\": \"" + name + "\",\n" +
	    	        "  \"age\": \""+ age + "\"\n" +
	    	        "}";
	  
	    	response =res.when()
	    			.contentType(ContentType.JSON)
	                .body(payload)
	    			.post("/");
	    	
	    }

	    @Then("^The response status should be 200 with valid Json$")
	    public void the_response_status_should_be_200_with_valid_json() throws Throwable {
	    	 resspec = new  ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();	 
	    	response = response.then().spec(resspec).extract().response();
	    	assertEquals(response.getStatusCode(),200);
	    }
	    
	    @And("^The response data of \"([^\"]*)\" should be \"([^\"]*)\"$")
	    public void the_response_data_of_something_should_be_something(String key, String strArg1) throws Throwable {
	    	String responseString = response.asString();
			JsonPath js = new JsonPath(responseString);
			assertEquals(js.getJsonObject(key),strArg1);
			
	    }

	
	
}
