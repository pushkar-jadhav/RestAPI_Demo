import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class basicAPI_1 
{
	@Test
	public void getAPI()
	{
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		given().
				param("location","-33.8670522,151.1957362").
				param("radius","500").
				param("key","AIzaSyD3jHRyQZItjdkyyxQHmlGgtGnPE61GkUk").
				when().
				get("/maps/api/place/nearbysearch/json").
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				body("results[0].name",equalTo("Sydney")).and().
				body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
				header("Server","pablo");
	}
}








//https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=YOUR_API_KEY