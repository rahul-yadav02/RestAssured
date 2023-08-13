package Authentication;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class BearerTokenAuthentication {

	@Test(priority=1)
	void testBearerAuthentication()
	{
		String bearerToken="token value";
		
		given()
			.header("Authorization","Bearer "+bearerToken)
		
		.when()
			.get("request url")
		
		.then()
			.statusCode(200)
			.log().all();
	}
}
