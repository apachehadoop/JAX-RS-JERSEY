package com.tutorial.ws.rest.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.tutorial.ws.rest.jersey.model.Message;
/**
 * 
 */
@Path("/messages")
public class JerseyRestController {

	@GET
	@Path("/text/{messageId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getMessageText(@PathParam("messageId") String messageId){
		String description = "JAX-RS Implementation using Jersey [Message id = "+messageId+" ]";

		return Response.status(Status.OK).entity(description).build();
	}
	
	@GET
	@Path("/xml/{messageId}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getMessagesXML(@PathParam("messageId") String messageId){
		String description = "JAX-RS Implementation using Jersey [Message id = "+messageId+" ]";
		Message message = new Message(messageId,description);
		return Response.status(Status.OK).entity(message).build();
	}
	
	@GET
	@Path("/json/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessagesJSON(@PathParam("messageId") String messageId){
		String description = "JAX-RS Implementation using Jersey [Message id = "+messageId+" ]";
		Message message = new Message(messageId,description);
		return Response.status(Status.OK).entity(message).build();
	}
	
	@POST
	@Path("/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addMessageFromXML(Message message){
		return Response.status(Status.OK).entity(message).build();
	}
	
	@POST
	@Path("/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessageFromJSON(Message message){
		return Response.status(Status.OK).entity(message).build();
	}
	
	@PUT
	@Path("/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response updateMessageFromXML(Message message){
		return Response.status(Status.OK).entity(message).build();
	}
	
	@PUT
	@Path("/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMessageFromJSON(Message message){
		return Response.status(Status.OK).entity(message).build();
	}
	
	@DELETE
	@Path("/{messageId}")
	public Response deleteMessage(@PathParam("messageId") String messageId){
		return Response.status(Status.OK).entity(messageId).build();
	}
	
	@GET
	@Path("/{messageId}")
	public Response getMessagePathVariable(@PathParam("messageId") String messageId){
		Message message = new Message(messageId, "Use of path variable @PathParam(\"messageId\") String messageId");
		return Response.status(Status.OK).entity(message).build();
	}
	
	@GET
	@Path("/message")
	@Produces(MediaType.APPLICATION_XML)
	public Response queryParams(@QueryParam("year") int year, @QueryParam("month") int month){
		Message message = new Message(year+""+month, " Using query param");
		return Response.status(Status.OK).entity(message).build();
	}
	
	@GET
	@Path("/matrixparam")
	@Produces(MediaType.APPLICATION_XML)
	public Response matrixParams(@MatrixParam("matrixparam")String value, @HeaderParam("customparam") String customHeader){
		Message message = new Message(value+" "+customHeader, " Matrix Param and Custom Header Param");
		return Response.status(Status.OK).entity(message).build();
	}
	
}