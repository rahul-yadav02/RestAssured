package ParsingJsonResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class parsingJsonResponseData {

	//@Test(priority=1)
	void testJsonResponse()
	{
		given()
			.contentType("application/json")
		
		.when()
			.get("http://localhost:3000/book")
			
		.then()
			.statusCode(200)
			.header("Content-Type","application/json; charset=utf-8")
			.body("book[2].title",equalTo("Dragon hunt"));
	}
	
	//@Test(priority=1)
		void testJsonResponse2()
		{
			Response res=given()
				.contentType(ContentType.JSON)
			
			.when()
				.get("http://localhost:3000/book");
				
			Assert.assertEquals(res.getStatusCode(), 200); //validation 1
			Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8"); //validation 2
			
			String book_name=res.jsonPath().get("book[2].title").toString();
			
			Assert.assertEquals(book_name,"Dragon Hunt");
		}
	
	@Test(priority=1)
	void testJsonResponse3()
	{
		Response res=given()
			 .contentType(ContentType.JSON)
	
		
		.when()
			.get("http://localhost:3000/book");
			
		//System.out.print(res.getBody().asString()); //to check what are we getting in response
		JSONObject jo=new JSONObject(res.asString()); //converting response to json object type
		
//		for(int i=0;i<jo.getJSONArray("book").length();i++)
//		{
//			String booktitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
//			System.out.println(booktitle);
//		}
		
		//check if title is present in our json or not
		
		boolean status=false;
		for(int i=0;i<jo.getJSONArray("book").length();i++)
		{
			String booktitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(booktitle.equals("Dragon hunt"))
			{
				status=true;
				break;
			}
			Assert.assertEquals(status, true);
		}
	}
}
