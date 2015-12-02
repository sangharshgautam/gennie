package org.telegram.client.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReplyKeyboardMarkup extends ReplyKeyboard{
	private String[][] keyboard;
	
	public ReplyKeyboardMarkup(){
		super();
	}
	
	private ReplyKeyboardMarkup(boolean selective){
		super(selective);
	}
	public static final ReplyKeyboardMarkup selective(String[][] keyboard){
		return new ReplyKeyboardMarkup(true).keyboard(keyboard);
	}
	
	@XmlElement(name = "resize_keyboard")
	private boolean resize;
	
	@XmlElement(name = "one_time_keyboard")
	private boolean oneTime;
	
	public ReplyKeyboardMarkup oneTime(){
		this.oneTime = true;
		return this;
	}
	private ReplyKeyboardMarkup keyboard(String[][] keyboard){
		this.keyboard = keyboard;
		return this;
	}
}
