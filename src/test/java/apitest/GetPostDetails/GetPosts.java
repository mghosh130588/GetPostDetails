package apitest.GetPostDetails;

import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//import org.junit.Test;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apitest.Utility.Utils;
import apitest.pojo.GetComment;
import apitest.pojo.GetPostChild;
import apitest.pojo.Users;

//import org.junit.Test;

//import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;


public class GetPosts extends Utils {
	
	
	public static ObjectMapper obj;
	@Before
	public void init() {
		
		
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}
	
	@Test
	public void getallposts() throws IOException {
		
		int userid = getUserID("Delphine");
		obj = new ObjectMapper();
		String res =	given().log().all()
				.queryParam("userId",userid)
				.header("Content-Type","application/json")
		.when().get("/posts")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.contentType("application/json").extract().asString();
		GetPostChild[] gc = obj.readValue(res, GetPostChild[].class);
		for (int j =0;j<gc.length;j++) {
			
			int postid = gc[j].getId();
			getComments(postid);
			
		}
		
		}
	
	public static int getUserID(String username) throws JsonMappingException, JsonProcessingException {
		
		String userlist = given().log().all().header("Content-Type","application/json")
				.when().get("/users")
				.then().log().all()
				.assertThat().statusCode(200)
				.contentType("application/json")
				.extract().asString();
		obj = new ObjectMapper();
		Users[] user = obj.readValue(userlist,Users[].class);
		for (int i = 0;i<user.length;i++) {
			
			if (user[i].getUsername().equalsIgnoreCase(username))
			{
				return(user[i].getId());
			}
		}
		
		
		return 0;
		
		
		
	}
	
	public static void getComments(int postid) throws IOException {
		
		String comments = given().log().all().header("Content-Type","application/json").queryParam("postId",postid)
		.when().get("/comments")
		.then().log().all().assertThat().statusCode(200)
		.contentType("application/json")
		.extract().asString();
		obj = new ObjectMapper();
		GetComment[] comment = obj.readValue(comments,GetComment[].class );
		if(comment.length!=0)
		{
			for(int k =0;k<comment.length;k++) {
				String email = comment[k].getEmail();
				if(Utils.emailValidator(email))
					Utils.log(email + " is valid \n");
				else
					Utils.log(email + " is not valid  \n");;
			}
			
		}
		
		else
			Utils.log("There is no comments for this post\n");
		
	}

	
}
