package ai.wit.api.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ai.wit.api.client.DurationUnit;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Normalized {
	private int value;
	private DurationUnit unit;
	@Override
	public String toString() {
		return "Normalized [value=" + value + ", unit=" + unit + "]";
	}
}
