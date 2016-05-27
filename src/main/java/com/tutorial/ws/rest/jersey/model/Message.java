package com.tutorial.ws.rest.jersey.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tutorial.ws.rest.jersey.Link;

@XmlRootElement
@XmlType(propOrder={"messageId","description","links"})
public class Message {

	private String messageId;
	private String description;
	
	List<Link> links = new ArrayList<Link>();
	
	public Message(){}
	
	public Message(String messageId, String description){
		this.messageId = messageId;
		this.description = description;
	}
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public void addLinks(String url,String rel){
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		getLinks().add(link);
	}
}