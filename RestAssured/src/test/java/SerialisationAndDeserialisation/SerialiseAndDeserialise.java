/*Serialisation pojo --- json
De-serialisation json -- pojo

We directly don't send object as request body because it heavy weight and not secure and anyone extract data from it so we convert it into json
which is light weight and then in response de-serialise json back to object
 */


package SerialisationAndDeserialisation;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import RestAssuredTest.Pojo_PostRequest;

public class SerialiseAndDeserialise {

	//@Test(priority=1)
	void convertPojo2Json() throws JsonProcessingException //serialisation pojo to json data
	{
		//created java object using pojo class
		Pojo_PostRequest data=new Pojo_PostRequest();
		
		data.setName("rocky");
		data.setLocation("France");
		data.setPhone("12344");
		String[] courseArr= {"GO","C++"};
		data.setCourses(courseArr);		
		
		//convert java object into json object
		ObjectMapper objMapper=new ObjectMapper();
		
		String jsondata=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		System.out.println(jsondata);
	}
	
	@Test(priority=1)
	void convertJson2Pojo() throws JsonProcessingException //Deserialisation json to pojo data
	{
		String jsondata="{\r\n"
				+ "  \"name\" : \"rocky\",\r\n"
				+ "  \"location\" : \"France\",\r\n"
				+ "  \"phone\" : \"12344\",\r\n"
				+ "  \"courses\" : [ \"GO\", \"C++\" ]\r\n"
				+ "}";
		
		//converting json object to pojo object
		ObjectMapper objMapper=new ObjectMapper();
		
		Student stuPojo=objMapper.readValue(jsondata,Student.class);
		System.out.println(stuPojo.getName());
		System.out.println(stuPojo.getLocation());
		System.out.println(stuPojo.getName());
		System.out.println(stuPojo.getCourses()[0]);
		System.out.println(stuPojo.getCourses()[1]);
		
	}
	
}
