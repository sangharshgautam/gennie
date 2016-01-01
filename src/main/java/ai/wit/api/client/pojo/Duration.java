package ai.wit.api.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ai.wit.api.client.DurationUnit;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Duration {
	private int hour;
	private int minute;
	private int second;
	private int value;
	private DurationUnit unit;
	private Normalized normalized;
	public Normalized normalized() {
		return normalized;
	}
}
