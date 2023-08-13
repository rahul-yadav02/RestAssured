/*This file is about ways to create request body
- HashMap
- Using org.json
- Using POJO (Plain Old Java Object)
- Using external json file
*/

package RestAssuredTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class WaystoCreateRequestBody {
	
	//using hashmap create user
	//@Test(priority=1)
	void testPostUsingHashMap()
	{
		HashMap data=new HashMap();
		data.put("name", "rocky");
		data.put("location", "bangalore");
		data.put("phone", "12344");
		
		String[] courseArr= {"GO","C++"};
		
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("rocky"))
			.body("location", equalTo("bangalore"))
			.body("phone", equalTo("12344"))
			.body("courses[0]",equalTo("GO"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-type","application/json; charset=utf-8")
			.log().all();
	}
	
	//Using org.json library
	//@Test(priority=1)
	void testPostOrgJson()
	{
		JSONObject data=new JSONObject();
		data.put("name", "rocky");
		data.put("location", "bangalore");
		data.put("phone", "12344");
		
		String[] courseArr= {"GO","C++"};
		
		data.put("courses", courseArr);		
		given()
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("rocky"))
			.body("location", equalTo("bangalore"))
			.body("phone", equalTo("12344"))
			.body("courses[0]",equalTo("GO"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-type","application/json; charset=utf-8")
			.log().all();
	}
	
	//Data creation is using POJO
	//@Test(priority=1)
		void testPostUsingPOJO()
		{
			Pojo_PostRequest data=new Pojo_PostRequest();
			data.setName("rocky");
			data.setLocation("France");
			data.setPhone("12344");
			
			String[] courseArr= {"GO","C++"};
			
			data.setCourses(courseArr);		
			given()
				.contentType("application/json")
				.body(data)
				
			.when()
				.post("http://localhost:3000/students")
				
			.then()
				.statusCode(201)
				.body("name", equalTo("rocky"))
				.body("location", equalTo("France"))
				.body("phone", equalTo("12344"))
				.body("courses[0]",equalTo("GO"))
				.body("courses[1]",equalTo("C++"))
				.header("Content-type","application/json; charset=utf-8")
				.log().all();
		}
	
	//Data creation is using external json file
	@Test(priority=1)
		void testPostUsingExternalJson() throws FileNotFoundException
		{
			//. dot is pointing to current file location, you can add full location also followed by json file name
			File f=new File(".\\body.json");
			FileReader fr=new FileReader(f);
			JSONTokener jt=new JSONTokener(fr);
			JSONObject data=new JSONObject(jt);
			
			data.put("name", "rocky");
			data.put("location", "France");
			data.put("phone", "12344");
			
			String[] courseArr= {"GO","C++"};
			
			data.put("courses", courseArr);		
			given()
				.contentType("application/json")
				.body(data.toString())
				
			.when()
				.post("http://localhost:3000/students")
				
			.then()
				.statusCode(201)
				.body("name", equalTo("rocky"))
				.body("location", equalTo("France"))
				.body("phone", equalTo("12344"))
				.body("courses[0]",equalTo("GO"))
				.body("courses[1]",equalTo("C++"))
				.header("Content-type","application/json; charset=utf-8")
				.log().all();
		}
	
	
	//Delete user
	@Test(priority=2)
	void testDelete()
	{
		given()
		
		.when()
			.delete("http://localhost:3000/students/13")
			
		.then()
			.statusCode(200);
	}

}
