package org.telegram.client.pojo;

import java.io.File;

public class SendableImage extends SendableText {
	
	private String caption;
	private File file;

	private SendableImage(String caption, ReplyKeyboardMarkup markup, File file) {
		super(caption, markup);
		this.caption = caption;
		this.file = file;
	}

	public String caption() {
		return caption;
	}

	public File file() {
		return file;
	}

	public static SendableImage create(String caption, ReplyKeyboardMarkup markup, File file) {
		return new SendableImage(caption, markup, file);
	}
	
}
