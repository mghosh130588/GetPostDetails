package apitest.Utility;

public enum APIResources {

	GETUSERS("/users"),
	GETPOSTS("/posts"),
	GETCOMMENTS("/comments");
	
	private String resource;
	APIResources(String resource){
		this.resource = resource;
		
	}
	
	public String getResource() {
		return resource;
		
		
	}
}
