package Authentication;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class AuthenticationTest {

	@Test(priority=1)
	void testBasicAuthentication() { //support basic, digestive & preemptive authentication backend algorithm will be diff but flow here will be same using id & pwd
		
		given()
			.auth().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true));
	}
	
	@Test(priority=2)
	void testDigestiveAuthentication() { //support basic, digestive & preemptive authentication backend algorithm will be diff but flow here will be same using id & pwd
		
		given()
			.auth().digest("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true));
	}
	
	@Test(priority=3)
	void testPreemptiveAuthentication() { //support basic, digestive & preemptive authentication backend algorithm will be diff but flow here will be same using id & pwd
		
		given()
			.auth().preemptive().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true));
	}
}
