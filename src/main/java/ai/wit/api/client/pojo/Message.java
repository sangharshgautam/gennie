package ai.wit.api.client.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
	@XmlElement(name = "msg_id")
	private String id;
	
	@XmlElement(name = "_text")
	private String text;
	
	private List<Outcome> outcomes;
	
	public Outcome firstOutcome(){
		return outcomes.isEmpty() ?  null : outcomes.get(0);
	}
}
