package org.telegram.client.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserProfilePhotos {
	
	@XmlElement(name="total_count", required = true)
	private int total;
	
	@XmlElement(required = true)
	private List<PhotoSize> photos;
}
