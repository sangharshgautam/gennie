package ai.wit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthRequest {
	private String q;
	private String access_token = WitClientTest.TOKEN;
	public AuthRequest(String q) {
		super();
		this.q = q;
	}
}
