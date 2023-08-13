package FileUpload;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class multipleFileUpload {

	@Test
	void testMultipleFilesUpload()
	{
		File file1=new File("File location");
		File file2=new File("File location");


		given()
			.multiPart("files",file1)
			.multiPart("files",file2)
			.contentType("multipart/form-data")
		
		.when()
			.post("URL Path")
		
		.then()
			.statusCode(200)
			.body("[0].filename", equalTo("file1.txt"))
			.body("[1].filename", equalTo("file2.txt"))
			.log().all();
	}
	
	@Test
	void testMultipleFilesUploadApproach2() 
	{
		File file1=new File("File location");
		File file2=new File("File location");

		File[] filearr= {file1,file2}; //This might not work for all APIs as it depends on how the API is implemented
		
		given()
			.multiPart("files",filearr)
			.contentType("multipart/form-data")
		
		.when()
			.post("URL Path")
		
		.then()
			.statusCode(200)
			.body("[0].filename", equalTo("file1.txt"))
			.body("[1].filename", equalTo("file2.txt"))
			.log().all();
	}
	
	@Test
	void fileDownload() {
		given()
		
		.when()
			.get("File uploaded url path")
		
		.then()
			.statusCode(200)
			.log().all();
	}
}
