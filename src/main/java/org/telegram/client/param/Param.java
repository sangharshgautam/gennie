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
	CAPTION,
	REPLY_TO_MESSAGE_ID,
	REPLY_MARKUP,
	PHOTO,
	ACTION,
	AUDIO,
	DURATION,
	PERFORMER,
	TITLE, 
	USER_ID,
	FILE_ID,
	PARSE_MODE,
	DISABLE_WEB_PAGE_PREVIEW,
	DOCUMENT,
	STICKER,
	VOICE,
	LATITUDE,
	LONGITUDE
	;
	
	public String getVal(){
		return this.toString().toLowerCase();
	}
}
