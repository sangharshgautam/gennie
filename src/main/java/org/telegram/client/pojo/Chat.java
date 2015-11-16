package org.telegram.client.pojo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="TG_CHAT")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING,length=10)
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
	
	@Column(name="TYPE")
	private String chatType;

	@XmlElement(name="type", required = true)
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
