package additionalFiles;

public class payLoad 
{
	public String getPlaceAddBody()
	{
		String addBody = "{"+
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
				"}";
		
		return addBody;
	}
	
	public String getPlaceDelBody(String id)
	{
		String delBody = "{"+
			  				"\"place_id\": \""+id+"\""+
			  			"}";
		
		return delBody;
	}
}
