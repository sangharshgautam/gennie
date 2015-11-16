package org.telegram.client.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="TG_USER")
public class User extends AbstractChat{

	@Column
	private String username; 	//Optional. User's or bot's username

	public User() {
		super();
	}

	private User(int id, String firstname, String lastName) {
		super(id, firstname, lastName);
	}
	
	public String getUsername() {
		return username;
	}

	public static User dummy() {
		return new User(345, "Dummy", "User");
	}
	
}
