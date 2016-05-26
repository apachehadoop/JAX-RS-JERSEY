# Restful WebServices Using JAX-RS (Maven and Jersey)
REST WebServices using Jersey and JAX-RS (JSR 311) specification.

Technologies used:
- 1.Jersey 1.19.1
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