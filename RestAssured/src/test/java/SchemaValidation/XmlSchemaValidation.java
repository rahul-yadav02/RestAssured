package SchemaValidation;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

public class XmlSchemaValidation {

	@Test
	void testXmlSchema()
	{
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler")
		
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("storeXmlSchema.xsd"));
	}
}
