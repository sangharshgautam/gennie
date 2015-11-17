package org.telegram.client.pojo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="PHOTO")
@DiscriminatorValue("PHOTO")
public class PhotoSize extends MediaMessage{
	
	private int width;
	private int height;
	
}
