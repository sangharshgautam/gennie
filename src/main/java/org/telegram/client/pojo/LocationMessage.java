package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationMessage implements Message{
	private Float longitude; 	//Longitude as defined by sender
	private Float latitude;		//Latitude as defined by sender
}
