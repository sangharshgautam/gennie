package uk.co.sangharsh.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Player {
	X,O;

	public Player getOpponent() {
		return X.equals(this) ? O: X;
	}
	public BufferedImage getImage() throws IOException{
		return ImageIO.read(new File(getThisCLassLoader().getResource(this.toString().toLowerCase()+".png").getFile()));
	}
	private ClassLoader getThisCLassLoader() {
		return getClass().getClassLoader();
	}
}
