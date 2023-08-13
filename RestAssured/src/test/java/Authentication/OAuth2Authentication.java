package Authentication;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class OAuth2Authentication {
	
	@Test(priority=1)
	void testOAuth2Authentication()
	{
		
		given()
			.auth().oauth2("accessToken")
		
		.when()
			.get("request url")
		
		.then()
			.statusCode(200)
			.log().all();
	}

}
