import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class basicAPI_2 {

	@Test
	public void postAPI()
	{
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		given().
			queryParam("key","AIzaSyD3jHRyQZItjdkyyxQHmlGgtGnPE61GkUk").
			body("{"+
				  "\"location\": {"+
				    "\"lat\": -33.8669710,"+
				    "\"lng\": 151.1958750"+
				  "},"+
				  "\"accuracy\": 50,"+
				  "\"name\": \"Google Shoes!\","+
				  "\"phone_number\": \"(02) 9374 4000\","+
				  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
				  "\"types\": [\"shoe_store\"],"+
				  "\"website\": \"http://www.google.com.au/\","+
				  "\"language\": \"en-AU\""+
				"}").
		when().
			post("/maps/api/place/add/json").
		then().
			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
			body("status",equalTo("OK"));
				
	}
}


//POST https://maps.googleapis.com/maps/api/place/add/json?key=YOUR_API_KEY HTTP/1.1