package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SendPhoto {
	private int chat_id;
	public SendPhoto() {
		
	}
	public SendPhoto(int chat_id) {
		super();
		this.chat_id = chat_id;
	}
	
}
