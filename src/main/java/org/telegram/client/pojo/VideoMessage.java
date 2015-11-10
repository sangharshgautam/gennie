package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VideoMessage extends StickerMessage{
	@XmlElement(required = true)
	private int duration; 	//Duration of the video in seconds as defined by sender
	
	@XmlElement(name="mime_type")
	private String mimetype; 	//Optional. Mime type of a file as defined by sender
	
}
