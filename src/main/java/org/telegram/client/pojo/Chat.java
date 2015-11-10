package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Chat extends Identifiable{
	@XmlElement(required = true)
	private int id; 	//Unique identifier for this user or bot
	
	@XmlElement(name="first_name", required = true)
	private String firstname; 	//String 	User's or bot's first name
	
	@XmlElement(name="last_name")
	private String lastName; 	//Optional. User's or bot's last name
	
	private String chatType; 	//Optional. User's or bot's last name

	public int getId() {
		return id;
	}

	@XmlElement(name="type")
	public void setChatType(String chatType) {
		this.chatType = chatType;
	}
	
}
