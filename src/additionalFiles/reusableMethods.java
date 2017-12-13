package additionalFiles;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class reusableMethods 
{
	public XmlPath rawToXML(Response response)
	{
		String xmlStringResp = response.asString();
		XmlPath xmlResp = new XmlPath(xmlStringResp);
		return xmlResp;
	}
	
	public JsonPath rawToJson(Response response)
	{
		String responseString = response.asString();
		JsonPath jsresp = new JsonPath(responseString);
		return jsresp;
	}
}
