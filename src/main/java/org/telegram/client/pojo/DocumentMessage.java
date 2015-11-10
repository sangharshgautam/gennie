package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentMessage extends MediaMessage{
	private PhotoSize thumb; 	//Optional. Document thumbnail as defined by sender
	
	@XmlElement(name="file_name")
	private String 	fileName; 	//Optional. Original filename as defined by sender
	
	@XmlElement(name="mime_type")
	private String mimeType; 	//Optional. MIME type of the file as defined by sender
}
