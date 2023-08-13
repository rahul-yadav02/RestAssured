package Authentication;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class APIKeyAuthentication {
	
	@Test(priority=1)
	void testAPIKeyAuthentication()
	{
		
		given()
			.queryParam("appid", "value")
		
		.when()
			.get("request url")
		
		.then()
			.statusCode(200)
			.log().all();
	}

}
