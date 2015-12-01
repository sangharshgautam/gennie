package org.telegram.client.param;

public enum Param {
	URL,
	OFFSET,
	LIMIT,
	TIMEOUT,
	CHAT_ID,
	FROM_CHAT_ID,
	MESSAGE_ID,
	TEXT,
	REPLY_TO_MESSAGE_ID,
	REPLY_MARKUP,
	PHOTO,
	ACTION,
	AUDIO,
	DURATION,
	PERFORMER,
	TITLE, 
	USER_ID,
	FILE_ID;
	
	public String getVal(){
		return this.toString().toLowerCase();
	}
}
