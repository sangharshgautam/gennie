package uk.co.sangharsh.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.telegram.client.pojo.ReplyKeyboardMarkup;
import org.telegram.client.pojo.SendableImage;
import org.telegram.client.pojo.User;
import org.telegram.client.type.Command;

import com.google.gson.Gson;

public class TicTacToe extends TwinPlayerGame{

	private static final String WHITE_SPACE = " ";

	private String[][] matrix;
	
	private BufferedImage template;
	
	public TicTacToe(User playerOne) throws IOException {
		super(playerOne);
		this.matrix  = new String[3][3];
		setTemplate();
	}

	private void setTemplate() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file1 = new File(classLoader.getResource("template.png").getFile());
		BufferedImage originalImg = ImageIO.read(file1);
		template = new BufferedImage(originalImg.getWidth(), originalImg.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		template.getGraphics().drawImage(originalImg, 0, 0, originalImg.getWidth(), originalImg.getHeight(), null);
	}

	public List<List<String>> keyboardX() {
		return keyboard("X");
	}
	public List<List<String>> keyboardO() {
		return keyboard("O");
	}

	private List<List<String>> keyboard(String p) {
		String player = p;
		if(p.trim().length() >1){
			player = p.substring(0, 1);
		}
		List<List<String>> keyboard = new ArrayList<List<String>>();
		for(int i=0;i<matrix.length ; i++){
			String[] data = matrix[i];
			List<String> row = new ArrayList<String>();
			for(int j = 1; j<=data.length;j++){
				String button = data[j-1];
				if(StringUtils.isBlank(button)){
					row.add(player+((3*i)+j));
				}else{
					row.add(WHITE_SPACE);
				}
			}
			keyboard.add(row);
		}
		keyboard.add(new ArrayList<String>(){{
			add(Command.QUIT.toString());
		}});
		return keyboard;
	}

	public TicTacToe move(Command command) throws IOException {
		String string = command.toString();
		if(StringUtils.length(string) == 2){
			
			String player = string.substring(0, 1);
			BufferedImage playerBi = ImageIO.read(new File(getThisCLassLoader().getResource(player.toLowerCase()+".png").getFile()));
			
			int block = Integer.parseInt(string.substring(1, 2));
			switch (block) {
			case 1:
				this.matrix[0][0] = player;
				drawMove(playerBi, 0+50, 0+100);
				break;
			case 2:
				this.matrix[0][1] = player;
				drawMove(playerBi, 110+50, 0+100);
				break;
			case 3:
				this.matrix[0][2] = player;
				drawMove(playerBi, 220+50, 0+100);
				break;
			case 4:
				this.matrix[1][0] = player;
				drawMove(playerBi, 0+50, 110+100);
				break;
			case 5:
				this.matrix[1][1] = player;
				drawMove(playerBi, 110+50, 110+100);
				break;
			case 6:
				this.matrix[1][2] = player;
				drawMove(playerBi, 220+50, 110+100);
				break;
			case 7:
				this.matrix[2][0] = player;
				drawMove(playerBi, 0+50, 220+100);
				break;
			case 8:
				this.matrix[2][1] = player;
				drawMove(playerBi, 110+50, 220+100);
				break;
			case 9:
				this.matrix[2][2] = player;
				drawMove(playerBi, 220+50, 220+100);
			default:
				break;
			}
				
		}
		boolean over = checkMate();
		return this;
	}

	private boolean checkMate() {
		for(int i = 0 ;i < this.matrix.length ;i++){
			boolean line = checkLine(this.matrix[i]);
			if(line){
				drawLine(i);
			}
		}
		return false;
	}

	private boolean checkLine(String[] row) {
		String mark = row[0];
		for(String colMark : row){
			if(!mark.equals(colMark)){
				return false;
			}
		}
		return WHITE_SPACE.equals(mark);
	}

	private ClassLoader getThisCLassLoader() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader;
	}
	private void drawLine(int i) {
		switch (i) {
		case 1:
			this.template.getGraphics().drawLine(0+50, 0+100, 220, 0+100);
			break;
		case 2:
			this.template.getGraphics().drawLine(110+50, 0+100, 220, 110+100);
			break;
		case 3:
			this.template.getGraphics().drawLine(220+50, 0+100, 220, 220+100);
			break;
		default:
			break;
		}
	}
	private void drawMove(BufferedImage playerBi, int x, int y) {
		this.template.getGraphics().drawImage(playerBi, x, y, playerBi.getWidth(), playerBi.getHeight(), null);
	}
	public static void main(String[] args) throws IOException {
		TicTacToe game = new TicTacToe(null);
		System.out.println(new Gson().toJson(game.keyboardX()));
		System.out.println(new Gson().toJson(game.move(Command.X2).keyboardX()));
		System.out.println(new Gson().toJson(game.move(Command.X5).keyboardX()));
	}

	public SendableImage reply(String command) throws IOException {
		
		File file = getBoard();
		String player = command;
		if(command.trim().length() >1){
			player = command.substring(0, 1);
		}
		List<List<String>> keyboard = keyboard(player);
		return SendableImage.create("Enter your move", ReplyKeyboardMarkup.selective(keyboard).oneTime(), file );
	}

	private File getBoard() throws IOException {
		File file = File.createTempFile("tictactoe", ""+System.currentTimeMillis()+".png");
		ImageIO.write(template, "png", file);
		return file;
	}
}
