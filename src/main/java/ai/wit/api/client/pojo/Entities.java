package ai.wit.api.client.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entities {
	private List<Action> action;
	private List<Duration> duration;
	
	public Duration firstDuration(){
		return duration.isEmpty() ?  null : duration.get(0);
	}
}
