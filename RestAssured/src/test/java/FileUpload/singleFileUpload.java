package FileUpload;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class singleFileUpload {

	@Test
	void testSingleFileUpload()
	{
		File myfile=new File("File location");

		given()
			.multiPart("file",myfile)
			.contentType("multipart/form-data")
		
		.when()
			.post("URL Path")
		
		.then()
			.statusCode(200)
			.body("filename", equalTo("filename"))
			.log().all();
	}
}
