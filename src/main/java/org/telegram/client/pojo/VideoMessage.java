package org.telegram.client.pojo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="VIDEO")
@DiscriminatorValue("VIDEO")
public class VideoMessage extends StickerMessage{
	@XmlElement(required = true)
	private int duration; 	//Duration of the video in seconds as defined by sender
	
	@XmlElement(name="mime_type")
	private String mimetype; 	//Optional. Mime type of a file as defined by sender
	
}
