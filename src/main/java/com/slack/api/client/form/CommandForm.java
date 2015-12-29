package com.slack.api.client.form;

import javax.ws.rs.FormParam;

public class CommandForm {
	@FormParam("token") 
	private String token;
	
	@FormParam("team_id") 
	private String teamId;
	
	@FormParam("team_domain") 
	private String teamDomain;
	
	@FormParam("channel_id") 
	private String channelId;
	
	@FormParam("channel_name") 
	private String channelName;
	
	@FormParam("user_id") 
	private String userId;
	
	@FormParam("user_name") 
	private String userName;
	
	@FormParam("command") 
	private String command;
	
	@FormParam("text") 
	private String text;
	
	@FormParam("response_url") 
	private String responseUrl;

	public String text() {
		return text;
	}

	@Override
	public String toString() {
		return "CommandForm [token=" + token + ", teamId=" + teamId
				+ ", teamDomain=" + teamDomain + ", channelId=" + channelId
				+ ", channelName=" + channelName + ", userId=" + userId
				+ ", userName=" + userName + ", command=" + command + ", text="
				+ text + ", responseUrl=" + responseUrl + "]";
	}
}
