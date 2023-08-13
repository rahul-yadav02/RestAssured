package PathAndQueryParams;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeaderDemo {
	
	//Hardcode fetch
	//@Test(priority=1)
	void testHeaders()
	{
		given()
		
		.when()
			.get("https://www.google.com")
		
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.header("content-Encoding","gzip")
			.header("Server","gws");
	}
	
	@Test(priority=1)
	void getHeaders()
	{
		Response res=given()
		
		.when()
			.get("https://www.google.com");
		
		//single header value fetch
//		String header_value=res.getHeader("Content-Type");
//		System.out.println("The value of content-type header is "+header_value);
		
		//Multiple header value fetch
		
		Headers myHeaders=res.getHeaders();
		for(Header hd:myHeaders)
		{
			System.out.println(hd.getName()+"  "+hd.getValue());
		}
		
	}
}
