# Restful WebServices Using JAX-RS (Maven and Jersey)
REST WebServices using Jersey and JAX-RS (JSR 311) specification.

Technologies used:
- Jersey 1.19.1
- JDK 1.7
- Tomcat 7.0
- Maven 3.0.3
- Eclipse Luna

### Configure Jersey ServletContainer in web.xml.

```
	java.tutorial.ws.rest is the path where container looks for the controller classes
    {ontextpath}/rest/* restful web services request path. Here sun jersey container used, there are different vendors are available in open market, for all implementation is same.
```
# Note: Jersey 1 and Jersey 2 are two different configurations do not mix them

```
 	<servlet>
		<servlet-name>jersey-serlvet</servlet-name>
		<servlet-class>
                     org.glassfish.jersey.servlet.ServletContainer
      </servlet-class>
		<init-param>
		     <param-name>jersey.config.server.provider.packages</param-name>
		     <param-value>com.tutorial.ws.rest.jersey</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>jersey-serlvet</servlet-name>
		<url-pattern>/*</url-pattern>
	</servlet-mapping>
```

### Create controller class map requests

URL : http://localhost:8080/rest/messages/1

Response : JAX-RS Implementation using Jersey [Message id = 1 ]
```
@Path("/messages")
public class JerseyRestController {
	
	@GET
	@Path("/{messageId}")
	public Response getMessages(@PathParam("messageId"){
	    String message = "JAX-RS Implementation using Jersey [Message id = "+messageId+" ]";
		return Response.status(Status.OK).entity(message).build();
		}
	
}
```

### GET
```
1.  Text response : 
Ref: getMessageText
URL : http://localhost:8080/rest/text/messages/1
Response : JAX-RS Implementation using Jersey [Message id = 1 ]
````
```
2.  XML Response:
Ref: getMessageXML
URL : http://localhost:8080/rest/xml/messages/4
Response :
<message>
   <description>JAX-RS Implementation using Jersey [Message id = 4 ]</description>
   <messageId>4</messageId>
</message>
```
```
3.  JSON Response
URL : http://localhost:8080/rest/json/messages/4
Ref: getMessageJSON
Response :
{
   "description": "JAX-RS Implementation using Jersey [Message id = 4 ]",
   "messageId": "4"
}
```
```
4.  XML Response (ordering properties)
URL : http://localhost:8080/rest/xml/messages/4
Add @XmlType(propOrder={"messageId","description"}) on Message Class
Ref: getMessageXML
URL : http://localhost:8080/rest/txml/messages/4
Response :
<message>
   <messageId>4</messageId>
   <description>JAX-RS Implementation using Jersey [Message id = 4 ]</description>
</message>
```

#POST

```
Consumes XML and Produces XML
Ref: addMesageFromXML
Use POSTMAN Tool -  for Testing
Request : Use Raw and Content-Type=application/xml
<message>
   <messageId>5</messageId>
   <description>JAX-RS Implementation using Jersey [Message id = 5 ]</description>
</message>

Response: XML
<message>
   <messageId>5</messageId>
   <description>JAX-RS Implementation using Jersey [Message id = 5 ]</description>
</message>

```
```
Consumes JSON and Produces JSON
Ref: addMesageFromJSON
Request : Use Raw data Content-Type=application/json
{
	"messageId":"99",
	"description":"JAX-RS Implementation using Jersey [Message id = 99 ]"
}
Response: JSON
{
	"messageId":"99",
	"description":"JAX-RS Implementation using Jersey [Message id = 99 ]"
}
```


#PUT

```
Consumes XML and Produces XML
Ref: updateMesageFromXML
Use POSTMAN Tool -  for Testing
Request : Use Raw and Content-Type=application/xml
<message>
   <messageId>555</messageId>
   <description>JAX-RS Implementation using Jersey [Message id = 555 ]</description>
</message>

Response: XML
<message>
   <messageId>555</messageId>
   <description>JAX-RS Implementation using Jersey [Message id = 555 ]</description>
</message>

```
```
Consumes JSON and Produces JSON
Ref: updateMesageFromJSON
Request : Use Raw data Content-Type=application/json
{
	"messageId":"9998",
	"description":"JAX-RS Implementation using Jersey [Message id = 9998 ]"
}
Response: JSON
{
	"messageId":"9998",
	"description":"JAX-RS Implementation using Jersey [Message id = 9998 ]"
}
```

#DELETE

```
URL http://localhost:8080/rest/messages/1

Ref: deleteMesage

Use POSTMAN Tool -  for Testing

```

#Path Variable


```
URL : http://localhost:8080/rest/messages/1
Ref: getMessagePathVariable

Response:
<message>
    <messageId>1</messageId>
    <description>Use of path variable @PathParam(&quot;messageId&quot;) String messageId</description>
</message>

```

#Query Param


```
URL : http://localhost:8080/rest/messages/message?year=2016&month=2
Ref: getMessagePathVariable

Response: produces XML 
<message>
<messageId>20162</messageId>
<description> Using query param</description>
</message>

```

#Matrix Params and Custom Headers

```
Ref:matrixParams
URL : http://localhost:8080/rest/messages/matrixparam;matrixparam=matrixvalue
<message>
	<messageId>matrixvalue Sreenivasa</messageId>
	<description> Matrix Param and Custom Header Param</description>
</message>

```

#Context 

```
URL: http://localhost:8080/rest/messages/context
Ref: contextParams  
Note : HttpHeaders can also be used to know the lot related request 
Response :http://localhost:8080/rest/messages/context 
```

#BeanParam

```
Create MessageBeanParam class and annotate properties as @QueryParam to make properties as query params
URL: http://localhost:8081/rest/messages/beanqueryparams?year=2016&month=2
Ref: beanQueryParams
Response: 
<message>
	<messageId>20162</messageId>
	<description> Using bean query param</description>
</message>
```

#Subresources

```
This way we can delegate and segregate responsibilities
URL : http://localhost:8081/rest/messages/1/comment

Ref:  getCommentsController

New Class : CommentsController act as sub resource
Response : Sub Resource

```

```
Retrieve Comment based on commentId using Sub resource implementation

URL: http://localhost:8081/rest/messages/1/comment/2
Ref:  getCommentsController & getComments

Response:
<comment>
    <messageId>1</messageId>
    <commentId>2</commentId>
    <description>Using sub resoure class</description>
</comment>
```

# Status codes

```
See the implementation of each method bellow line Response.status(Status.OK).entity(message).build();

```

# URL Location Headers

```
URL:http://localhost:8081/rest/messages/message
Ref: addNewMessage
Response : Body
<message>
    <messageId>111</messageId>
    <description>JAX-RS Implementation using Jersey [Message id = 111 ]</description>
</message>
Response : Header

Location : http://localhost:8081/rest/messages/message/111
```

# Exception Handling

```
URL : http://localhost:8081/rest/messages/100
Ref: getMessagePathVariable

Response: 
......
 <h1>HTTP Status 500 - java.lang.RuntimeException: Object not found !</h1>
......
```

# Exception Mapper

```
URL : http://localhost:8081/rest/messages/404
Ref: getMessagePathVariable
<errorMessage>
    <documentation>http://localhost:8080/rest/</documentation>
    <errorCode>404</errorCode>
    <errorMessage>Object not found !</errorMessage>
</errorMessage>
```

# Generic Exception [Also see GenericException Mapper]

```
Using WebApplicationException, NotFoundException etc,..   Check Api for more exception
URL : http://localhost:8081/rest/messages/500
Ref: getMessagePathVariable
Response:
<errorMessage>
    <documentation>http://localhost:8080/rest/</documentation>
    <errorCode>500</errorCode>
    <errorMessage>Message not found</errorMessage>
</errorMessage>

```

# HATEOAS 

```
URL: http://localhost:8081/rest/messages/message/hateoas/10
Ref:hateoas

Response:
<message>
    <messageId>10</messageId>
    <description> How to use HATEOAS</description>
    <links>
        <link>http://localhost:8081/rest/messages/10</link>
        <rel>self</rel>
    </links>
</message>

```

# Content Negotiation

```
Use content negotiation to change format of the response.
Response will changes based on the clients request Content-type  

```