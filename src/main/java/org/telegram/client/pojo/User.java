package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

	@XmlElement(required = true)
	private int id; 	//Unique identifier for this user or bot
	
	@XmlElement(name="first_name", required = true)
	private String firstname; 	//String 	User's or bot's first name
	
	@XmlElement(name="last_name")
	private String lastName; 	//Optional. User's or bot's last name
	
	private String username; 	//Optional. User's or bot's username

	public User() {
		super();
	}

	private User(int id, String firstname) {
		super();
		this.id = id;
		this.firstname = firstname;
	}

	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastName="
				+ lastName + ", username=" + username + "]";
	}

	public static User dummy() {
		return new User(345, "Dummy");
	}
	
}
