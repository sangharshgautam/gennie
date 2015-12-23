package uk.co.sangharsh.nlp.resource.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Utterance {
	private String speaker;
	private String text;

	public Utterance() {
		super();
	}

	private Utterance(String speaker, String text) {
		super();
		this.speaker = speaker;
		this.text = text;
	}
	public static final Utterance utterance(String speaker, String text) {
		return new Utterance(speaker, text);
	}
	public String speaker() {
		return speaker;
	}

	public String text() {
		return text;
	}
}
