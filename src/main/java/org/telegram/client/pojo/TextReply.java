package org.telegram.client.pojo;

import uk.co.sangharsh.client.commons.pojo.Sendable;

public class TextReply implements Sendable {

	private String text;

	public TextReply(){
		super();
	}
	public TextReply(String text) {
		super();
		this.text = text;
	}

	@Override
	public String inLine() {
		return this.text;
	}
	public static TextReply thank(User user) {
		return new TextReply(user.firstname()+" Thanks for connecting! Gennie will be back soon!");
	}

}
