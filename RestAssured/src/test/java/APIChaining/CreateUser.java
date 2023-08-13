package APIChaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class CreateUser {
	
	@Test
	void createUser(ITestContext Context) {
		
		Faker faker=new Faker();
		
		JSONObject data=new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("Status", "inactive");
		
		String bearerToken="ff343b745f4fa86ce722f8e4864ed1b6e56dd73afa5367614d2f3b319b51aedb";
		
		int id=given()
			.header("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		
		// Print the response body
		//System.out.println("Response Body: " + res.getBody().asString());
		
		System.out.println(id);
		
		//test level which will work for other classes within same test class 
		Context.setAttribute("user_id",id); //consider this like environment variable like we do in postman and refer to other request
		
		//suitelevel to across multiple test
		//Context.setSuiteAttribute("user_id",id);
		
		
		/*TestNG xml file is used to run multiple tests and it will generate report also
		Right click on packagae where you want to run all class and
		then select testng and click convert to testng
		then in prompt browse your package and click finish
		your testng.xml file is now create*/
		
	}

}
