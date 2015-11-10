package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MediaMessage implements Message{
	@XmlElement(name="file_id", required = true)
	private String fileId;
	
	@XmlElement(name="file_size")
	private int fileSize;
}
