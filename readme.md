# Intro
This application is a simple API that allows users to create and read Ideas.
My solution is build with using JWT tokens in mind. Security context is mocked so it is only possible to create ideas in context of one user.
In real application it would be possible to create ideas in context of multiple users. User ID would be stored in JWT token and used to create ideas.
Idea db repository should check is allowed to create or read ideas in context of user ID stored in JWT token. Since I was asked to design and implement API I did not focus on checking user IDs.
Media is also simplified. In real application it would be possible to store any kind of media: images, videos, audio. For now I only implemented image idea.

Application is build with Spring Boot.

# Questions :
1. What technologies would you choose?
2. What database(s) would you choose?
3. What considerations would you make when building the API?
4. How would you handle the security of the API?
5. Write the Slack message you would send to the frontend developer explaining
   how to use the API

# Answers :
1. - I've used spring framework. It allows to build application fast and easy. It has a lot of features that are useful in building API. 
It is also easy to implement security with spring in the real world scenario.
- I've added OpenAPI to the project. It allows to generate documentation for API. It is also possible to generate client code from OpenAPI specification.
- For the file storage I would use AWS S3.
2. I would use NoSQL database because I think this case is document oriented. There are no complex queries. The only relationship is between user and ideas but can be handled. For storing files I would use AWS S3.
3. I would use OAuth2 to secure API. It is possible to use JWT tokens with OAuth2. If there would be a need for fetching specific data based on requirements I would consider GraphQL.
For fetching data I would use pagination. API I have implemented has MEDIA type ideas. I assume that user could store any kind of media: images, videos, audio. For know I only implemented image idea. If I were to implement other types of media I would have to add information about what type of media it is (what is the extension of the file). I would also add validation for file size and file type.
I think that returning media in a response for a list of ideas is not a good idea. Better solution is to have separate endpoint for fetching media.
4. I would use OAuth2 with JWT tokens. For identity and access management we can use Keycloak. HTTPS should be used to secure communication between client and server.
5. Hi, I've implemented API for creating and reading ideas for the Jira issue XYZ-123 (link). I've deployed it on dev environment. You can find documentation here: http://localhost:8080/swagger-ui/index.html#/ideas-rest-controller (this should be a link to dev environment).
Quick explaination how to use it: 
- to create idea you need to send POST request to /ideas endpoint. You should set content type to application/json if you want to add text idea or url idea. For image idea you should set content type to multipart/form-data. For now only JPEG files are allowed. You should always set type field to determine what type of idea you want to create. For text idea you should set text field. For url idea you should set url field. For image idea you should set file field.
- to read ideas list you need to send GET request to /ideas endpoint. You can also read single idea by sending GET request to /ideas/{id} endpoint. Response will contain idea data and image if it is image idea. Based on the type field you can determine what type of idea it is.
For now, it is possible to create ideas only in context of one user (hardcoded). In the future it would be possible to create ideas in context of multiple users. User ID would be stored in JWT token and used to create ideas, but it's not implemented in this version.
Let me know if you have any questions.


   
