package apitest.cucumber.stepdefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apitest.Utility.APIResources;
import apitest.Utility.Utils;
import apitest.pojo.GetComment;
import apitest.pojo.GetPostChild;
import apitest.pojo.Users;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefinition extends Utils {
	
	
	public static ObjectMapper obj;
	public int userid = 0;
	public  GetPostChild[] gc;
	public boolean getposts = true;
	public boolean getcomments = true;
	public  ArrayList <GetComment> comment;
	
	@Given("Get the userId for the username {string} with {string} request")
	public void get_the_userId_for_the_username_with_request(String username, String resource) throws IOException {
	    APIResources res = APIResources.valueOf(resource);
	    
		String userlist = given().spec(Utils.requestspecification())
				.when().get(res.getResource())
				.then()
				.spec(ResponseSpecification())
				.extract().asString();
		obj = new ObjectMapper();
		Users[] user = obj.readValue(userlist,Users[].class);
		for (int i = 0;i<user.length;i++) {
			
			if (user[i].getUsername().equalsIgnoreCase(username))
			{
				userid = user[i].getId();
				
			}
			
		}
		if(userid ==0)
		{
			Utils.log("The username doesnot exists \n");
			getposts = false;
			getcomments = false;

			
		}

		
	}


	@When("the user sends a {string} request to {string} for the above UserID")
	public void the_user_sends_a_request_to_for_the_above_UserID(String reqtype, String resource) throws IOException {
		APIResources resq = APIResources.valueOf(resource);
		if(getposts)
		{
		obj = new ObjectMapper();
		String res =	given().spec(Utils.requestspecification())
				.queryParam("userId",userid)
		.when().get(resq.getResource())
		.then().spec(ResponseSpecification())
		.extract().asString();
		gc = obj.readValue(res, GetPostChild[].class);
		if(gc.length==0)
		{
			Utils.log("there is no posts for this user");
			getcomments = false;
		}
		}
		
	}

	@When("The user sends a {string} request to {string} for all the posts")
	public void the_user_sends_a_request_to_for_all_the_posts(String method, String resources) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		if(getcomments)
		{
		APIResources resq = APIResources.valueOf(resources);
		comment = new ArrayList<GetComment>();
		if(gc.length!=0)
		{
		for (int j=0;j<gc.length;j++)
		{
			int postid = gc[j].getId();
		String comments = given().spec(Utils.requestspecification())
				.queryParam("postId",postid)
				.when().get(resq.getResource())
				.then().spec(ResponseSpecification())
				.extract().asString();
				obj = new ObjectMapper();
				GetComment[] comment1 = obj.readValue(comments,GetComment[].class );
				comment.addAll(Arrays.asList(comment1));
				
			}
		}

		}
	}
	




	@Then("validate that the {string} address present in each comment is valid and print the email address in a log file")
	public void validate_that_the_address_present_in_each_comment_is_valid_and_print_the_email_address_in_a_log_file(String string) throws IOException {
	   if(getcomments) 
	   {
		if(!comment.isEmpty())
		{
			for(int k = 0;k<comment.size();k++)
			{
				String email = comment.get(k).getEmail();
				if(Utils.emailValidator(email))
					Utils.log(email + " is valid \n");
				else
					Utils.log(email + " is not valid  \n");;
			}
		
				
			}
		
		else 
			Utils.log("there is no comments for any posts for this users");
			
		}
	}
	
}



