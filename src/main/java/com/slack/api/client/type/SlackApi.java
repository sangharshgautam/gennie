package com.slack.api.client.type;

import org.apache.commons.lang3.StringUtils;

public interface SlackApi {
	public enum Channel implements Scope{
		WRITE,
		HISTORY,
		READ;
		public String sName(){
			return StringUtils.join("channels", ":", this.toString().toLowerCase().replaceAll("_", ":"));
		}
	}
	public enum Chat implements Scope{
		WRITE_USER,
		WRITE_BOT;
		public String sName(){
			return StringUtils.join("chat", ":", this.toString().toLowerCase().replaceAll("_", ":"));
		}
	}
	
}
