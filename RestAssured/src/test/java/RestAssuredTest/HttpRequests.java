//This file is about how to send request using restassured framework

/*
Create your own APIs using below process:
- Download NodeJS
- npm(node package manager) will come under nodejs check environment variables path/nodejs/ & path/nodejs/nodejs_modules
- Download json-server using npm install -g json-server 
*/

package RestAssuredTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

/*
Gerkin language
Methods:

Given() includes:
content type, set cookies, add auth, add param, set headers info etc...

When() includes:
get, put, post, delete

Then() includes:
validate status code, extract response, extract headers cookies & response body...

reqres.in website for APIs

import static packages in every package from this url and add above
https://github.com/rest-assured/rest-assured/wiki/GettingStarted
io.restassured.RestAssured.*
io.restassured.matcher.RestAssuredMatchers.*
org.hamcrest.Matchers.*
*/

public class HttpRequests {
	
	int id;
	
	@Test(priority=1)
	void getUsers()
	{
		//Whichever method starts first do not require to start with . operator
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all(); //Display all response in the console
	}
	
	@Test(priority=2)
	void createUser()
	{
		HashMap data=new HashMap();
		data.put("name", "pavan");
		data.put("job", "trainer");
		
		id=given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
//		.then()
//			.statusCode(201)
//			.log().all();	
	}
	
	@Test(priority=3, dependsOnMethods= {"createUser"})
	void UpdateUser()
	{
		HashMap data=new HashMap();
		data.put("name", "rahul");
		data.put("job", "engineer");
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(200)
			.log().all();	
	}
	
	@Test(priority=4)
	void deleteUser()
	{
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
			
		.then()
			.statusCode(204)
			.log().all();
	}

}
