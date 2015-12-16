package com.slack.api.client.pojo.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.slack.api.client.pojo.Message;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PostMessageResponse extends SlackResponse<Message> {
	private String channel;
	private String ts;
	private Message message;
}
