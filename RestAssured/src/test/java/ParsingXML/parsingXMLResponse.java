package ParsingXML;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class parsingXMLResponse {

	//@Test(priority=1)
	void testXMLResponse()
	{
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
			
		.then()
			.statusCode(200)
			.header("Content-Type","application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page",equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name",equalTo("Developer"));
	}
	
	//@Test(priority=1)
		void testXMLResponseVariable()
		{
			Response res=given()
			
			.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1");
				
			Assert.assertEquals(res.statusCode(), 200);
			Assert.assertEquals(res.contentType(),"application/xml; charset=utf-8");
			
			String pageno=res.xmlPath().get("TravelerinformationResponse.page").toString();
			Assert.assertEquals(pageno, "1");
			
			String travelname=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
			Assert.assertEquals(travelname, "Developer");
		}
	
	@Test(priority=1)
	void testXMLResponseVariableAdv()
	{
		Response res=given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
			
		XmlPath xmlobj=new XmlPath(res.asString()); //for converting entire response into string use asString() and convert data into string use toString()
		
		List<String> travellers=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(travellers.size(), 10);
		
		//verify traveller name is present in response
		List<String> travellers_name=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		
		boolean status=false;
		for(String name:travellers_name)
		{
			if(name.equals("Developer"))
			{
				status=true;
				break;
			}
			Assert.assertEquals(status, true);
		}

	}
}
