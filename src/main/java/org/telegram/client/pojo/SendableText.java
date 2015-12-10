package org.telegram.client.pojo;

import uk.co.sangharsh.client.commons.pojo.Sendable;

public class SendableText implements Sendable {

	private String text;

	public SendableText(){
		super();
	}
	protected SendableText(String text) {
		super();
		this.text = text;
	}

	public static final SendableText create(String text){
		return new SendableText(text);
	}
	@Override
	public String inLine() {
		return this.text;
	}
	public static SendableText thank(User user) {
		return new SendableText(user.firstname()+" Thanks for connecting! Gennie will be back soon!");
	}

}
