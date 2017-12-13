import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import additionalFiles.payLoad;
import additionalFiles.resources;
import additionalFiles.reusableMethods;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;


public class basicAPI_3 {
	
	Properties pro;
	resources res= new resources();
	payLoad pl = new payLoad();
	reusableMethods rm = new reusableMethods();
	
	@BeforeTest
	public void getData() throws IOException
	{
		pro = new Properties();
		File file = new File(System.getProperty("user.dir")+"/src/additionalFiles/env.properties");
		FileInputStream fis = new FileInputStream(file);
		pro.load(fis);
	}
	
	@Test
	public void deleteAPI()
	{
		RestAssured.baseURI = pro.getProperty("hosturl");
		
		//grab the response
		Response resp = given().
							queryParam("key",pro.getProperty("apikey")).
							body(pl.getPlaceAddBody()).
						when().
							post(res.addPlaceData()).
						then().
							assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status",equalTo("OK")).
						extract().
							response();
		JsonPath jsonresp = rm.rawToJson(resp);
		String placeId = jsonresp.get("place_id");
		System.out.println(placeId);
		
		//Place the above place_id in delete request
		String delBody = pl.getPlaceDelBody(placeId);
		
		given().
			queryParam("key", pro.getProperty("apikey")).
			body(delBody).
		when().
			post(res.delPlaceData()).
		then().
			assertThat().statusCode(200).and().body("status", equalTo("OK")).and().contentType(ContentType.JSON);
	}

}


//POST https://maps.googleapis.com/maps/api/place/delete/json?key=YOUR_API_KEY HTTP/1.1
