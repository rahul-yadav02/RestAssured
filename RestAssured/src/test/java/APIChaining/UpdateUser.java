package APIChaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class UpdateUser {
	
	@Test
	void updateUser(ITestContext Context) {
		
		
		Faker faker=new Faker();
		
		JSONObject data=new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("Status", "active");
		
		
		String bearerToken="ff343b745f4fa86ce722f8e4864ed1b6e56dd73afa5367614d2f3b319b51aedb";
		int id=(Integer)Context.getAttribute("userid"); //this should come from create user request

		
		 given()
			.header("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
			.pathParam("id", id)
		
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")

		.then()
			.statusCode(200)
			.log().all();
		
	}

}
