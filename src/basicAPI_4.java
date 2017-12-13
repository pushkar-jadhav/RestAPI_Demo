import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import additionalFiles.reusableMethods;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class basicAPI_4 {

	reusableMethods rm = new reusableMethods();
	@Test
	public void postAPI() throws IOException
	{
		RestAssured.baseURI = "https://maps.googleapis.com";
		String postAddData = generateStringFromResource(System.getProperty("user.dir")+"/src/additionalFiles/placeAddRequestData.xml");
		
		Response resp = given().
			queryParam("key","AIzaSyD3jHRyQZItjdkyyxQHmlGgtGnPE61GkUk").
			body(postAddData).
		when().
			post("/maps/api/place/add/xml").
		then().
			assertThat().statusCode(200).and().contentType(ContentType.XML).and().
		extract().response();
			
		XmlPath xmlResp = rm.rawToXML(resp);
		System.out.println(xmlResp.get("PlaceAddResponse.place_id"));
	}
	
	public static String generateStringFromResource(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	
}


//POST https://maps.googleapis.com/maps/api/place/add/xml?key=YOUR_API_KEY HTTP/1.1