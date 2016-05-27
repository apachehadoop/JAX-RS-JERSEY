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
```
<servlet>
		<servlet-name>jersey-serlvet</servlet-name>
		<servlet-class>
                     com.sun.jersey.spi.container.servlet.ServletContainer
      </servlet-class>
		<init-param>
		     <param-name>com.sun.jersey.config.property.packages</param-name>
		     <param-value>java.tutorial.ws.rest.jersey</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>jersey-serlvet</servlet-name>
		<url-pattern>/rest/*</url-pattern>
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
URL http://localhost:8081/rest/messages/1

Ref: deleteMesage

Use POSTMAN Tool -  for Testing

```

#Path Variable


```
URL : http://localhost:8081/rest/messages/1
Ref: getMessagePathVariable

Response:
<message>
    <messageId>1</messageId>
    <description>Use of path variable @PathParam(&quot;messageId&quot;) String messageId</description>
</message>

```

#Query Param


```
URL : http://localhost:8081/rest/messages/message?year=2016&month=2
Ref: getMessagePathVariable

Response: produces XML 
<message>
<messageId>20162</messageId>
<description> Using query param</description>
</message>

```

#Matrix Params and Custom Headers

```
URL : http://localhost:8081/rest/messages/matrixparam;matrixparam=matrixvalue
<message>
	<messageId>matrixvalue Sreenivasa</messageId>
	<description> Matrix Param and Custom Header Param</description>
</message>
Ref:matrixParams

```