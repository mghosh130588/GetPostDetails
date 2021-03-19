# GetPostDetails
Steps to run the test:

Pre-Requisites:
1- Git and Maven should be installed in the workspace.
2- You can have any IDE(eclipse ) installed in your machine / workspace.

Steps:
1- Copy the project from the Git Reporitory by using the folowing command.
git clone https://github.com/mghosh130588/GetPostDetails.git
2- move to project parent location where the pom.xml is located.
cd /GetPostDetails
3- Run the below maven commands to run build/ run the tests.
mvn clean verify
mvn test
4- Verify that the Build is success and test is executed. Verify the Log details (loggin.txt, output.txt) created at project parent location where pom.xml is placed.
