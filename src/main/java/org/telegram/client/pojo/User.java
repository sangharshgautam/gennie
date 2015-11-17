package org.telegram.client.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="TG_USER")
public class User extends Identifiable{

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
	private String username; 	//Optional. User's or bot's username

	public User() {
		super();
	}

	private User(int id, String firstname, String lastName) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return username;
	}

	public static User dummy() {
		return new User(345, "Dummy", "User");
	}

	@Override
	public int getId() {
		return this.id;
	}

	public String firstname() {
		return firstname;
	}
	
}
