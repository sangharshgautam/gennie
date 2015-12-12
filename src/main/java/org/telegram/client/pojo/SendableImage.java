package org.telegram.client.pojo;

import java.io.File;

public class SendableImage extends SendableText {
	private File file;

	private SendableImage(String text, File file) {
		super(text);
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public static SendableImage create(String caption, File file) {
		return new SendableImage(caption, file);
	}
	
}
