# AWS-Spring-Boot-Deployment
A simple web-application has been created using Spring Boot Framework, and we have exposed two REST API endpoints (one for GET request, and second for POST request). The GET endpoint will be used to get the list of books and POST method will be used to enter new book to the existing list of books. 

The web application has been deployed over AWS Elastic Beanstalk and the REST API endpoints have been exposed via AWS API Gateway. Finally, the logs for the application have been redirected to AWS Cloudwatch for detailed logging.

# Pre-requisites

* AWS Cloud Account
* High level knowledge for AWS Elastic Beanstalk, API Gateway and AWS Cloudwatch
* Low level knowledge for Spring Boot Framework and Java 8

# Step 1: Create a Spring Boot Application and create a jar using mvn clean install command

Look into the code above for explanation

_Below screenshot shows the jar generated that will be used for deployment to AWS Elastic Beanstalk_

<img width="959" alt="jar_deployed" src="https://user-images.githubusercontent.com/34195659/163556518-bd39d705-213f-4e81-bfd2-f62bc6627b32.PNG">


# Step 2: Login to AWS Console and create an environment in AWS Elastic Beanstalk

The Spring Boot Application has been deployed on this environment created via Elastic Beanstalk.

The main advantage of using Elastic Beanstalk:

* We can quickly deploy and manage applications in the AWS Cloud without having to learn about the infrastructure that runs those applications. 
* Elastic Beanstalk reduces management complexity without restricting choice or control. 
* We can simply upload our application, and Elastic Beanstalk automatically handles the details of capacity provisioning, load balancing, scaling,
application health monitoring etc.

_Below screenshot shows the book-service environment created via Elastic Beanstalk_

<img width="955" alt="ElasticBeanstalkEnvs" src="https://user-images.githubusercontent.com/34195659/163557744-cf54ccaa-6461-4d98-bd5e-84ecae200050.PNG">


# Step 3: Deploy the application to Elastic Beanstalk

_Below screenshots show the API request hit post deployment of our application to Elastic Beanstalk_

POST request to enter a new book to the existing list of books

<img width="500" alt="PostmanPostReq" src="https://user-images.githubusercontent.com/34195659/163558690-8cbd2b45-0a5d-4506-9265-77b68fd4d307.PNG">

GET request to get entire list of books

<img width="596" alt="endpointSnap" src="https://user-images.githubusercontent.com/34195659/163558218-dc5f592b-1b7c-49ad-932a-a34e415d4e9f.PNG">

Now once the application is UP and RUNNING, the API endpoints in the Spring Boot Application have been exposed using AWS API Gateway

# Step 4: Using API Gateway, expose the GET and POST endpoints for the application deployed on Elastic Beanstalk

Create a new REST API using API Gateway

<img width="500" alt="api_creation" src="https://user-images.githubusercontent.com/34195659/163559281-c1929ca7-0fba-4ffe-97d2-9c178c6e5c96.PNG">

Add the details for all endpoints in application

<img width="945" alt="api-gateway-creation" src="https://user-images.githubusercontent.com/34195659/163559309-0973d01e-d0cd-47df-b400-6196bbf0190e.PNG">

Create a stage for deployment of API endpoints. We have created production stage for our application

<img width="718" alt="deploy-api-prod" src="https://user-images.githubusercontent.com/34195659/163559451-3db7d861-41db-46b9-ac86-412402dd60cd.PNG">

# Step 5: Verification of API endpoints post deployment to API Gateway

GET method execution

<img width="948" alt="api-flow-apigateway" src="https://user-images.githubusercontent.com/34195659/163560426-4a07671f-1d79-4ffe-b4c2-d4e61dcf3882.PNG">

POST method execution

<img width="952" alt="api-flow-apigateway-postrequest" src="https://user-images.githubusercontent.com/34195659/163560445-e7bb8f12-6569-4bee-a112-4551d1268b92.PNG">

Note the 'Invoke URL' will be used to hit the REST API endpoints

<img width="933" alt="hit-get-api" src="https://user-images.githubusercontent.com/34195659/163560919-e41dd2a4-d6c6-4787-84ca-3cee6e7570b6.PNG">

POST request for adding a new book to the list. _Note the URL for API Gateway_

<img width="662" alt="postman-integrated-request" src="https://user-images.githubusercontent.com/34195659/163560570-05fd5e1a-f8b2-4735-a49f-19bc9fe9d77a.PNG">

GET request to get the list of books. We can see the new book added to existing list of books. _Note the URL for API Gateway_ 

<img width="1424" alt="Screenshot 2022-04-15 at 4 03 40 PM" src="https://user-images.githubusercontent.com/34195659/163561228-9b33568c-8809-4fd8-a25b-1eaacd9423d9.png">

To enhance the application logging, all application logs have been redirected to AWS Cloudwatch. But, we cannot directly add the AWS Cloudwatch logging to API Gateway since it requires specific IAM role. Since, by default its not allowed by AWS to use AWS Cloudwatch for logging with API Gateway.

# Step 6: Add the managed IAM role for using AWS Cloudwatch with API Gateway

Create a new role to add the required permissions for using AWS Cloudwatch with API Gateway

<img width="949" alt="iam-policy-cloudwatch-logs" src="https://user-images.githubusercontent.com/34195659/163562136-9d768181-c8b8-4487-8132-dd009e240647.PNG">

More specified details for the IAM Role

<img width="951" alt="iam-policy-cloudwatch-logs-rules" src="https://user-images.githubusercontent.com/34195659/163562170-d9d93ab4-7648-4bc2-9d87-e49f1c351352.PNG">

New IAM Role has been created

<img width="950" alt="iam-rule-created" src="https://user-images.githubusercontent.com/34195659/163562252-9d422cbe-bc58-4c1d-bc9b-590a21b1cbaa.PNG">


# Step 7: Add the ARN received from IAM Role to the API Gateway and enable Cloudwatch logging for API Gateway

_Refer below screenshot_

<img width="957" alt="rule-inserted-in-apigateway" src="https://user-images.githubusercontent.com/34195659/163562747-c1286379-eeed-43f9-8d9e-20b968331a00.PNG">

_Below screenshot shows enabling Cloudwatch logging for our application deployed on API Gateway_

<img width="952" alt="final-settings-for-cloudwatch" src="https://user-images.githubusercontent.com/34195659/163563119-c645ba2a-5615-42a9-9c75-c83c58b601cc.PNG">


# Step 8: Using AWS Cloudwatch for application logs

_Below screenshot shows the log group getting created for the API Gateway. Check the last log group ending with /production_

<img width="950" alt="log-groups-created" src="https://user-images.githubusercontent.com/34195659/163562899-d4a566cc-9967-41d6-b237-f9eeb12a2161.PNG">

Sending a new POST API request with new book details

<img width="662" alt="postman-integrated-request" src="https://user-images.githubusercontent.com/34195659/163563298-36a3e042-a50b-41ee-b7c2-628df906ee67.PNG">

Sending a GET request to receive entire list of books

<img width="1424" alt="Screenshot 2022-04-15 at 4 03 40 PM" src="https://user-images.githubusercontent.com/34195659/163563455-ea3e75ea-c2c0-46f6-9887-02e6498ada07.png">

Below screenshot shows the new cloudwatch logs generated

<img width="950" alt="cloudwatch-logs-seen" src="https://user-images.githubusercontent.com/34195659/163563566-1be3d19f-5819-4eb4-986a-488cc883b4d6.PNG">

Detailed logs with details of API request sent

<img width="934" alt="detailed-cloudwatch-logs" src="https://user-images.githubusercontent.com/34195659/163563667-7a6d296c-d58f-470d-a7a8-9bd2424cea20.PNG">

POST request logs

<img width="940" alt="more-detailed-cloudwatch-logs" src="https://user-images.githubusercontent.com/34195659/163563756-5edb4f31-f38a-4187-bdc6-ec840306832c.PNG">

GET request logs

<img width="952" alt="get-request-cloudwatch-logs" src="https://user-images.githubusercontent.com/34195659/163563811-f76a3c9d-b24f-4f48-8ba7-adb820e939c5.PNG">


Thus, we have successfully deployed our web application using Elastic Beanstalk and API Gateway using AWS Cloudwatch for logging the application logs.
