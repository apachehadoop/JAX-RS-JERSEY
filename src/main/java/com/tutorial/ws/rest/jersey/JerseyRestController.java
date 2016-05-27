package com.tutorial.ws.rest.jersey;

import java.net.URI;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.tutorial.ws.rest.jersey.exception.ErrorMessage;
import com.tutorial.ws.rest.jersey.exception.MessageNotFound;
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
		
		if("100".equals(messageId)){
			throw new  RuntimeException("Object not found !");
		}
		if("404".equals(messageId)){
			throw new  MessageNotFound("Object not found !");
		}
		if("500".equals(messageId)){
			ErrorMessage  error= new  ErrorMessage();
			error.setErrorCode(500);error.setErrorMessage("Message not found");error.setDocumentation("http://localhost:8080/rest/");
			Response res = Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
			throw new  WebApplicationException(res);
		}
		if("400".equals(messageId)){
			ErrorMessage  error= new  ErrorMessage();
			error.setErrorCode(500);error.setErrorMessage("Message not found");error.setDocumentation("http://localhost:8080/rest/");
			Response res = Response.status(Status.NOT_FOUND).entity(error).build();
			throw new  NotFoundException(res);
		}
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

	@GET
	@Path("/context")
	public String contextParams(@Context UriInfo uriInfo, @Context  HttpHeaders header){
		String head = header.getCookies().toString();
		return uriInfo.getAbsolutePath().toString()+ " Cookies : "+head;
	}
	
	@GET
	@Path("/beanqueryparams")
	@Produces(MediaType.APPLICATION_XML)
	public Response beanQueryParams(@BeanParam MessageBeanParam beanParam){
		Message message = new Message(beanParam.getYear()+""+beanParam.getMonth(), " Using bean query param");
		return Response.status(Status.OK).entity(message).build();
	}
	
	@Path("/{messageId}/comment")
	public CommentsController getCommentsController(){
		return new CommentsController();
	}

	@POST
	@Path("/message")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Response addNewMessage(Message message,@Context UriInfo uriInfo){
		/** Store message and return new message */
		String messageId = message.getMessageId();
		URI uri = uriInfo.getAbsolutePathBuilder().path(messageId).build();
		
		return Response.created(uri).status(Status.OK).entity(message).build();
	}
	
	@GET
	@Path("/message/hateoas/{messageId}")
	@Produces(MediaType.APPLICATION_XML)
	public Message hateoas(@PathParam("messageId") String messageId, @Context UriInfo uriInfo){
		/** Store message and return new message */
		Message message = new Message(messageId, " How to use HATEOAS");
		String uri = uriInfo.getBaseUriBuilder().path(JerseyRestController.class).path(messageId).build().toString();
		message.addLinks(uri, "self");
		return message;
	} 
}