package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class ReplyKeyboard {
	
	private boolean selective;
	protected ReplyKeyboard(){}
	
	protected ReplyKeyboard(boolean selective){
		this.selective = selective;
	}
	
	public String json(){
		return "";
	}
}
