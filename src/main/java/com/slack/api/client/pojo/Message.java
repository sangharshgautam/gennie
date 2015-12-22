package com.slack.api.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
	private String text;
	private String user;
	private String type;
	private String subtype;
	private String ts;
	public String text() {
		return this.text;
	}
	public boolean isMyBot(){
		return "U0GKLB5FC".equals(this.user);
	}
}
