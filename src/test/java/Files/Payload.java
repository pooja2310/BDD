package Files;

public class Payload
{
	public static String addPlace()
{
	return "{\n" +
			"    \"name\": \"morpheus\",\n" +
			"    \"job\": \"leader\",\n" +
			"    \"id\": \"28\",\n" +
			"    \"createdAt\": \"2023-08-13T09:51:34.526Z\"\n" +
			"}";
}

public static String addBook()
{
	String payload= "{\n" +
			"\n" +
			"\"name\":\"Learn Appium Automation with Java\",\n" +
			"\"isbn\":\"23\",\n" +
			"\"aisle\":\"12\",\n" +
			"\"author\":\"John foe\"\n" +
			"}";
	return payload;
}
	
	public static String deleteBook()
	{
		return "{\n" +
				" \n" +
				"\"ID\" : \"2312\"\n" +
				" \n" +
				"}";
	}
}

