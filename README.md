# Project Setup Guidelines
<p>This is a regular spring based (java) rest application which uses <b>gradle</b> as package manager.

## Local Environment

Step 1. Set up AWS Credentials and Region for Development <br/>
* Create AWS <i><b>credentials</b></i> file based on your operating system <br/>
Location : ~/.aws/credentials on Linux, macOS, or Unix <br/>
Location : C:\Users\USERNAME\.aws\credentials on Windows

```
[default]
aws_access_key_id = your_access_key_id
aws_secret_access_key = your_secret_access_key
```
* Create AWS <i><b>config</b></i> file based on your operating system <br/>
```
[default]
#this is our default environment for Europe region
region = eu-north-1
output = json
```
Step 2.  Clone repository <br/>

Clone the master branch
```
git clone https://<bitnucket-user>@bitbucket.org/walakulu/micro-db-generator.git
```

Step 2.  Clean & Build the Project <br/>
Use regular gradle build commands as you use in other projects.<br/>
* Ex: You can use below command to clean and build the project using gradle wrapper
```
./gradlew clean build
```

Step 2.  Run the Project <br/>
Use run button of your preferred IDE or use below command to run the build artifact.

```
java -jar ./build/libs/<build-artifact>.jar
```

Ex:
```
java -jar ./build/libs/micro-db-modeler-0.0.1-SNAPSHOT.jar
```
## Test & Production Environments 

* We use jenkins to build and deploy our <b>test</b> & <b>production</b> environments.<br/>

Note: It is recommended to configure Jump host server in future to make this system more secure.