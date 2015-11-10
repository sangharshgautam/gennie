package org.telegram.client.param;

public enum Param {
	URL,
	OFFSET,
	LIMIT,
	TIMEOUT,
	CHAT_ID,
	TEXT,
	REPLY_TO_MESSAGE_ID,
	REPLY_MARKUP,
	PHOTO,
	ACTION;
	
	public String getVal(){
		return this.toString().toLowerCase();
	}
}
