package APIChaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {
	
	@Test(priority=1)
	void testGetUser(ITestContext Context)
	
	{
		int id=(Integer)Context.getAttribute("userid"); //this should come from create user request
		String bearerToken="ff343b745f4fa86ce722f8e4864ed1b6e56dd73afa5367614d2f3b319b51aedb";
		
		given()
			.header("Authorization","Bearer "+bearerToken)
			.pathParam("id", id)
		
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
			.statusCode(200)
			.log().all();
	}

}
