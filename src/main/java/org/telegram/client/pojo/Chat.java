package org.telegram.client.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="TG_CHAT")
public class Chat extends Identifiable{
	
	@XmlElement(required = true)
	@Id
	private int id; 	//Unique identifier for this user or bot
	
	@XmlElement(name="first_name", required = true)
	@Column
	private String firstname; 	//String 	User's or bot's first name
	
	@XmlElement(name="last_name")
	@Column
	private String lastName; 	//Optional. User's or bot's last name
	
	@Column
	private String chatType; 	//Optional. User's or bot's last name

	@XmlTransient
	public void setChatType(String chatType) {
		this.chatType = chatType;
	}

	public String getChatType() {
		return chatType;
	}

	@Override
	public int getId() {
		return id;
	}
}
