package org.telegram.client.pojo;

import uk.co.sangharsh.client.commons.pojo.Sendable;

public class SendableText implements Sendable {

	private String text;
	
	private ReplyKeyboardMarkup markup;

	public SendableText(){
		super();
	}
	protected SendableText(String text, ReplyKeyboardMarkup markup) {
		super();
		this.text = text;
		this.markup = markup;
	}

	public static final SendableText create(String text){
		return new SendableText(text, null);
	}
	public static final SendableText create(String text, ReplyKeyboardMarkup keyboard){
		return new SendableText(text, keyboard);
	}
	@Override
	public String inLine() {
		return this.text;
	}
	public static SendableText thank(User user) {
		return SendableText.create(user.firstname()+" Thanks for connecting! Gennie will be back soon!");
	}
	public ReplyKeyboardMarkup markup() {
		return markup;
	}
}
