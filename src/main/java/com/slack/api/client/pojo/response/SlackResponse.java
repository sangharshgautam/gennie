package com.slack.api.client.pojo.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class SlackResponse<T> {
	private boolean ok;
	
	private String error;
	
	public boolean isOk() {
		return ok;
	}

	public String error() {
		return error;
	}
	
}
