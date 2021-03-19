package apitest.Utility;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class Init {

	@BeforeClass
	public static void init() {
		
		
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		File f = new File("output.txt");
		if(f.exists())
		{
			f.delete();
		}
	}
}
