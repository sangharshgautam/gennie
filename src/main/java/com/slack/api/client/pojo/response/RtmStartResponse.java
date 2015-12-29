package com.slack.api.client.pojo.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RtmStartResponse extends SlackResponse<String>{
	private boolean ok;
	private String url;
	public boolean isOk() {
		return ok;
	}
	public String wsUrl() {
		return url;
	}
}
