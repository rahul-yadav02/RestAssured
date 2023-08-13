package PathAndQueryParams;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;

public class CookiesDemo {
	
	@Test(priority=1)
	void testCookies()
	{
		given()
		
		.when()
			.get("https://www.google.com")
		
		.then()
			.cookie("AEC","Ad49MVGCTg_1n5msjPk-jWBI9EqvbArEQULSeP3cx03TjelAxhiJIZZD0P0") //cookie value will change that's the behaviour of cookie
			.log().all();
	}
	
	@Test(priority=2)
	void getCookiesInfo()
	{
		
		Response res=given()
		
		.when()
			.get("https://www.google.com");
		
		// get single cookie info
//		String cookie_value=res.getCookie("AEC");
//		System.out.println("Value of cookie is"+cookie_value);
		
		//get all cookies info
		Map<String,String> cookies_values=res.getCookies();
		for(String k:cookies_values.keySet())
		{
			String cookie_value=res.getCookie(k);
			System.out.println(k+"   "+cookie_value);
		}
	}
}
