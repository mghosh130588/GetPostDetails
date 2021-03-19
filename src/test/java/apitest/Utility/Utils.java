package apitest.Utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.validator.routines.EmailValidator;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification reqs;
	
	public static RequestSpecification requestspecification() throws FileNotFoundException {
		
		if(reqs==null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("loggin.txt"));
		RequestSpecBuilder rspec = new RequestSpecBuilder();
		reqs = rspec.addHeader("Content-Type","application/json")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return reqs;
		}
		return reqs;
		
	}
public ResponseSpecification ResponseSpecification() {
	
	ResponseSpecification response = new ResponseSpecBuilder().expectContentType("application/json").expectStatusCode(200).build();
	return response;
	
	
}
	public static boolean emailValidator(String email) {
		 
        // Get an EmailValidator
        EmailValidator validator = EmailValidator.getInstance();
 
        // Validate specified String containing an email address
        if (!validator.isValid(email)) {
            return false;
        }
        return true;
    }
	
	
	 public static void log(String message) throws IOException { 
	      PrintWriter out = new PrintWriter(new FileWriter("output.txt", true), false);
	      out.write(message);
	      out.close();
	      
	    }
}
