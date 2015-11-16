package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageResult extends AbstractResult<Telegram>{
	private Telegram result;
	public Telegram getResult() {
		return result;
	}
}
