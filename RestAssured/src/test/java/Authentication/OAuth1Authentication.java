package Authentication;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class OAuth1Authentication {
	
	@Test(priority=1)
	void testOAuth1Authentication()
	{
		
		given()
			.auth().oauth("consumerKey", "consumerSecrate", "accessToken", "tokenSecrate")
		
		.when()
			.get("request url")
		
		.then()
			.statusCode(200)
			.log().all();
	}

}
