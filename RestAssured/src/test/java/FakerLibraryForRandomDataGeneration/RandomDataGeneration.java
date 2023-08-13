package FakerLibraryForRandomDataGeneration;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class RandomDataGeneration {
	
	@Test(priority=1)
	void randomDataGenerate()
	{
		
		Faker faker=new Faker();
		
		String fullname=faker.name().fullName();
		String firstname=faker.name().firstName();
		String lastname=faker.name().lastName();
		
		String username=faker.name().username();
		String password=faker.internet().password();
		
		String phoneno=faker.phoneNumber().cellPhone();
		String email=faker.internet().safeEmailAddress();
		
		System.out.println("Full Name "+fullname);
	}

}
