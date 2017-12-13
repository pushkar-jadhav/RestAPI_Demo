import org.testng.annotations.Test;

import additionalFiles.reusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class basicAPI_5 
{
	reusableMethods rm = new reusableMethods();
	@Test
	public void getAPI()
	{
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		Response resp = given().
								param("location","-33.8670522,151.1957362").
								param("radius","500").
								param("key","AIzaSyD3jHRyQZItjdkyyxQHmlGgtGnPE61GkUk").log().all().
						when().
								get("/maps/api/place/nearbysearch/json").
						then().log().all().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
								body("results[0].name",equalTo("Sydney")).and().
								body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
								header("Server","pablo").
						extract().
								response();
		
		JsonPath jsresp = rm.rawToJson(resp);
		
		int size = jsresp.get("results.size()");
		for(int i=0;i<size;i++)
		{
			System.out.println(jsresp.get("results["+i+"].name"));
		}
		//System.out.println(size);
	}
}








//https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=YOUR_API_KEY