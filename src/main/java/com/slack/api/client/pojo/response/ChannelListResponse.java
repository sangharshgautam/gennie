package com.slack.api.client.pojo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.slack.api.client.pojo.Channel;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ChannelListResponse extends SlackResponse<List<Channel>> {
	private List<Channel> channels;

	public List<Channel> channels() {
		return channels;
	}
	
}
