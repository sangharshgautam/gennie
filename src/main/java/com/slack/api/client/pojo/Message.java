package com.slack.api.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.slack.api.client.type.MessageSubType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
	private String text;
	private String user;
	private String username;
	private String type;
	private String subtype;
	private String ts;
	public String text() {
		return this.text;
	}
	public boolean isToIgnore(){
		return this.text.toUpperCase().contains("SUMMARIZE") || MessageSubType.FILE_SHARE.toString().equals(subtype) || MessageSubType.BOT_MESSAGE.toString().equals(subtype);
	}
	public String user() {
		return user;
	}
	public String ts() {
		return ts;
	}
}
