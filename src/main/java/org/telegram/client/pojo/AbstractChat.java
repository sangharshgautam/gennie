package org.telegram.client.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@MappedSuperclass
public abstract class AbstractChat extends Identifiable{
	@XmlElement(required = true)
	@Id
	private int id; 	//Unique identifier for this user or bot
	
	@XmlElement(name="first_name", required = true)
	@Column
	private String firstname; 	//String 	User's or bot's first name
	
	@XmlElement(name="last_name")
	@Column
	private String lastName; 	//Optional. User's or bot's last name
	
	public AbstractChat(){
		
	}
	public AbstractChat(int id, String firstname, String lastName) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}
}
