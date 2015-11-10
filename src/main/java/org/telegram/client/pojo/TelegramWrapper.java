package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TelegramWrapper {
	private boolean ok;
	private Telegram result;
	public boolean isOk() {
		return ok;
	}
	public Telegram getResult() {
		return result;
	}
	
}
