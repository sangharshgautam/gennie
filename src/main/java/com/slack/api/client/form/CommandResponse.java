package com.slack.api.client.form;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CommandResponse {
	@XmlElement(name = "response_type")
	private String responseType;
	
	@XmlElement
	private String text;
	
	private List<String> attachments;
	
	public CommandResponse(){}
	public CommandResponse(String text) {
		super();
		this.text = text;
	}
	public static final CommandResponse using(String text) {
		return new CommandResponse(text);
	}
}