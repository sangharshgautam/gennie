package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactMessage extends Message{
	@XmlElement(name="phone_number", required = true)
	private String phoneNumber; //String 	Contact's phone number
	
	@XmlElement(name="first_name", required = true)
	private String firstName; 	//Contact's first name

	@XmlElement(name="last_name")
	private String lastName; 	//Optional. Contact's last name
	
	@XmlElement(name="user_id")
	private int userId; 	//Optional. Contact's user identifier in Telegram

	@Override
	public String toString() {
		return "ContactMessage [phoneNumber=" + phoneNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", userId=" + userId
				+ "]";
	}
	
}
