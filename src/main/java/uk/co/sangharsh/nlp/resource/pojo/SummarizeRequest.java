package uk.co.sangharsh.nlp.resource.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SummarizeRequest {
	private int lines;
	
	private String text;

	public int lines() {
		return lines;
	}

	public String text() {
		return text;
	}

	@Override
	public String toString() {
		return "SummarizeRequest [lines=" + lines + ", text=" + text + "]";
	}

	private SummarizeRequest(int lines, String text) {
		super();
		this.lines = lines;
		this.text = text;
	}

	public SummarizeRequest() {
		super();
	}
	public static SummarizeRequest using(int lines, String text) {
		return new SummarizeRequest(lines, text);
	}
}
