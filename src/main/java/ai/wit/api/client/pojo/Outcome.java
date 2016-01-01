package ai.wit.api.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Outcome {
	@XmlElement(name = "_text")
	private String text;
	private double confidence;
	private String intent;
	
	private Entities entities;

	public Entities entities() {
		return entities;
	}
	
}
