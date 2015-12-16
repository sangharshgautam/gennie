package com.slack.api.client.pojo.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.slack.api.client.pojo.Ping;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PingResponse extends SlackResponse<Ping> {

	@XmlElement(name = "args")
	private Ping ping;
}
