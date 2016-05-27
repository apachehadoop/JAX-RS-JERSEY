package com.tutorial.ws.rest.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	public Response addMesageFromXML(Message message){
		return Response.status(Status.OK).entity(message).build();
	}
	
	@POST
	@Path("/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMesageFromJSON(Message message){
		return Response.status(Status.OK).entity(message).build();
	}
	
}