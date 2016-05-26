package com.tutorial.ws.rest.jersey.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"messageId","description"})
public class Message {

	private String messageId;
	private String description;
	
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
}