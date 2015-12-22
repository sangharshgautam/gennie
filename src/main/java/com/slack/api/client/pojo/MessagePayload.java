package com.slack.api.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MessagePayload {
	private String text;
	public MessagePayload(){}
	private MessagePayload(String text) {
		super();
		this.text = text;
	}
	public static MessagePayload payload(String text) {
		return new MessagePayload(text);
	}
}
