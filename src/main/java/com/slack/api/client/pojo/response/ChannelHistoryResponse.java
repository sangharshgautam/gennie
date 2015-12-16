package com.slack.api.client.pojo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.slack.api.client.pojo.Channel;
import com.slack.api.client.pojo.Message;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ChannelHistoryResponse extends SlackResponse<List<Channel>> {
	private List<Message> messages;
	
	@XmlElement(name = "has_more")
	private boolean more;

	public List<Message> messages() {
		return messages;
	}

	public boolean hasMore() {
		return more;
	}
	
}
