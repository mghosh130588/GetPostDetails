Feature: Get all the comments in a post by a given username and validate the email address in the comments the comments, for all the posts for a given username.
	
	
	Scenario: to get all the comments posted in the all the posts for the user with username Delphine
	
	Given Get the userId for the username "Delphine" with "GETUSERS" request
	When the user sends a "GET" request to "GETPOSTS" for the above UserID 
	And The user sends a "GET" request to "GETCOMMENTS" for all the posts
	Then validate that the "email" address present in each comment is valid and print the email address in a log file
	
	Scenario: to get all the comments posted in the all the posts for the user with username Delph
	
	Given Get the userId for the username "Delph" with "GETUSERS" request
	When the user sends a "GET" request to "GETPOSTS" for the above UserID 
	And The user sends a "GET" request to "GETCOMMENTS" for all the posts
	Then validate that the "email" address present in each comment is valid and print the email address in a log file
	
	Scenario: to get all the comments posted in the all the posts for the user with username Delphine
	
	Given Get the userId for the username "Delphine" with "GETUSERS" request
	When the user sends a "GET" request to "GETPOSTS" for the above UserID 
	And The user sends a "GET" request to "GETCOMMENTS" for all the posts
	Then validate that the "email" address present in each comment is valid and print the email address in a log file