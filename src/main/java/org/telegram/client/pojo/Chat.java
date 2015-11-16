package org.telegram.client.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="TG_CHAT")
public class Chat extends AbstractChat{
	
	@Column
	private String chatType; 	//Optional. User's or bot's last name

	@XmlElement(name="type")
	public void setChatType(String chatType) {
		this.chatType = chatType;
	}

	public String getChatType() {
		return chatType;
	}
}
