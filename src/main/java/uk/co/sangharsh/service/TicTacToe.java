package uk.co.sangharsh.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.telegram.client.pojo.ReplyKeyboardMarkup;
import org.telegram.client.pojo.SendableImage;
import org.telegram.client.pojo.User;
import org.telegram.client.type.Command;

import com.google.gson.Gson;

public class TicTacToe extends TwinPlayerGame{

	private static final String WHITE_SPACE = " ";

	private PlayerMove[] matrix;
	
	private BufferedImage template;

	private Player player;
	
	public TicTacToe(User playerOne) throws IOException {
		super(playerOne);
		this.matrix  = new PlayerMove[9];
		setTemplate();
	}

	private void setTemplate() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file1 = new File(classLoader.getResource("template.png").getFile());
		BufferedImage originalImg = ImageIO.read(file1);
		template = new BufferedImage(originalImg.getWidth(), originalImg.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		template.getGraphics().drawImage(originalImg, 0, 0, originalImg.getWidth(), originalImg.getHeight(), null);
	}

	public List<List<String>> keyboard() {
		return keyboard(this.player);
	}

	private List<List<String>> keyboard(Player player) {
		List<List<String>> keyboard = new ArrayList<List<String>>();
		keyboard.add(getRow(1));
		keyboard.add(getRow(2));
		keyboard.add(getRow(3));
		keyboard.add(getRow4());
		return keyboard;
	}

	private List<String> getRow4() {
		List<String> quitRow = new ArrayList<String>();
		quitRow.add(Command.QUIT.toString());
		return quitRow;
	}

	private List<String> getRow(int index) {
		List<String> row = new ArrayList<String>();
		row.add(matrixValue(index-1));
		row.add(matrixValue(index));
		row.add(matrixValue(index+1));
		return row;
	}
	private String matrixValue(int index) {
		return matrix[index] !=null ? WHITE_SPACE : this.player.toString()+(index+1);
	}
	public TicTacToe move(Command command, Player player) throws Exception {
		String string = command.toString();
		if(StringUtils.length(string) == 2){
			Move move = Move.valueOf("MOVE"+string.substring(1, 2));
			if(this.matrix[move.getIndex()] !=  null){
				throw new Exception("Invalid Move");
			}else{
				this.matrix[move.getIndex()] = new PlayerMove(player, move);
				drawMove(player.getImage(), move);
			}
		}
		boolean mate = checkMate();
		if(!mate && player.equals(this.player)){
			return systemMove();
		}
		return this;
	}
	public TicTacToe move(Command command) throws Exception {
		return move(command, this.player);
	}
	private TicTacToe systemMove() throws Exception {
		//check own doubles to win
		boolean winPossible = isWinningMove(matrix[0], matrix[1], matrix[2]);
		if(winPossible){
			winTheGame(matrix[0], matrix[1], matrix[2]);
		}else{
			for(int i =0;i<matrix.length;i++){
				PlayerMove playerMove =  matrix[i];
				if(playerMove == null){
					return move(Command.valueOf(this.player.getOpponent().toString()+(i+1)), this.player.getOpponent());
				}
			}

		}
		return this;
	}


	private void winTheGame(PlayerMove... playerMoves) {
		
	}

	private boolean isWinningMove(PlayerMove...playerMoves) {
		Player system = this.player.getOpponent();
		int counter =0;
		for(PlayerMove playerMove : playerMoves){
			if(playerMove!=null){
				if(this.player.equals(playerMove.player())){
					return false;
				}else if(system.equals(playerMove.player())){
					counter++;
				}
			}
		}
		return counter ==2;
	}

	private boolean checkMate() {
		boolean row1 = checkLine(matrix[0], matrix[1], matrix[2]);
		boolean row2 = checkLine(matrix[3], matrix[4], matrix[5]);
		boolean row3 = checkLine(matrix[6], matrix[7], matrix[8]);
		
		boolean col1 = checkLine(matrix[0], matrix[3], matrix[6]);
		boolean col2 = checkLine(matrix[1], matrix[4], matrix[7]);
		boolean col3 = checkLine(matrix[2], matrix[5], matrix[8]);
		
		
		boolean diagonal1 = checkLine(matrix[0], matrix[4], matrix[8]);
		boolean diagonal2 = checkLine(matrix[2], matrix[4], matrix[6]);
		boolean checkmate = BooleanUtils.xor(new boolean[]{row1, row2, row3, col1, col2, col3, diagonal1, diagonal2});
		return checkmate;
	}

	private boolean checkLine(PlayerMove... row) {
		if(ArrayUtils.contains(row, null)){
			return false;
		}else{
			boolean end = row[0].player().equals(row[1].player()) && row[1].player().equals(row[2].player());
			if(end){
				drawLine(row[0], row[2]);
			}
			return end; 
		}
	}

	private ClassLoader getThisCLassLoader() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader;
	}
	private void drawLine(PlayerMove a, PlayerMove b) {
		this.template.getGraphics().drawLine(a.move().getX(), a.move().getY(), b.move().getX(), b.move().getY());
	}
	private void drawMove(BufferedImage playerBi, Move move) {
		this.template.getGraphics().drawImage(playerBi, move.getX(), move.getY(), playerBi.getWidth(), playerBi.getHeight(), null);
	}
	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();

//		System.out.println(new GsonBuilder().setPrettyPrinting().serializeNulls().create().toJson(src));
		List<List<String>> keyboard = new TicTacToe(null).set(Player.X).move(Command.X1).move(Command.X5).move(Command.X8).keyboard();
		
		System.out.println(gson.toJson(keyboard));
		System.out.println(gson.toJson(new TicTacToe(null).set(Player.X).move(Command.X4).move(Command.X5).move(Command.X6).keyboard()));
		System.out.println(gson.toJson(new TicTacToe(null).set(Player.X).move(Command.X7).move(Command.X8).move(Command.X9).keyboard()));
		
		System.out.println(gson.toJson(new TicTacToe(null).set(Player.X).move(Command.X1).move(Command.X4).move(Command.X7).keyboard()));
		System.out.println(gson.toJson(new TicTacToe(null).set(Player.X).move(Command.X2).move(Command.X5).move(Command.X8).keyboard()));
		System.out.println(gson.toJson(new TicTacToe(null).set(Player.X).move(Command.X3).move(Command.X6).move(Command.X9).keyboard()));
		
		
		System.out.println(gson.toJson(new TicTacToe(null).set(Player.X).move(Command.X1).move(Command.X5).move(Command.X9).keyboard()));
		System.out.println(gson.toJson(new TicTacToe(null).set(Player.X).move(Command.X3).move(Command.X5).move(Command.X7).keyboard()));

	}

	public SendableImage reply(String command) throws IOException {
		
		File file = getBoard();
		String player = command;
		if(command.trim().length() >1){
			player = command.substring(0, 1);
		}
		List<List<String>> keyboard = keyboard(Player.valueOf(player));
		return SendableImage.create("Enter your move", ReplyKeyboardMarkup.selective(keyboard).oneTime(), file );
	}

	private File getBoard() throws IOException {
		File file = File.createTempFile("tictactoe", ""+System.currentTimeMillis()+".png");
		ImageIO.write(template, "png", file);
		return file;
	}

	public TicTacToe set(Player player) {
		this.player = player;
		return this;
	}
}
